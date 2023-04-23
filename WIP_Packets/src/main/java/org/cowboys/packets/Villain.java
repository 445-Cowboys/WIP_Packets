package org.cowboys.packets;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Villain extends PacketFactory{

    public static final int MAX_DATA_LENGTH = 100;
    public static final int MAX_NAME_LENGTH = MAX_DATA_LENGTH - 26;
    public static final int MIN_DATA_LENGTH = 0;

    //4 bytes per int, 2 bytes per boolean, 2 * name.length bytes for String
    private int Id; //[0] - [3]
    private int health; //[4] - [7]
    private int attack; //[8] - [11]
    private int defense; //[12] - [15]
    private int ammo; //[16] - [19]
    private boolean isAlive; //Data type uses two bytes but only 21 matters [20][21]
    private int nameLength; // [22] - [25]
    private String name;//Varchar 74 [26] - [99]
    private int maxAmmo = 6;

    public Villain(int type, String name, int health, int attack, int defense, int ammo,
                   InetAddress address, int port ){
        super(address, port, type);

        this.Id = type;
        this.name = name;
        this.nameLength = name.length();
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.ammo = ammo;
        this.isAlive = true;
    }

    public Villain(DatagramPacket packet, int type) {
        super(packet, type);

        byte[] data = packet.getData();
        int offset = packet.getOffset();

        //Before
        this.Id = ByteBuffer.wrap(data, offset, 4).getInt();
        this.health = ByteBuffer.wrap(data, offset + 4, 4).getInt();
        this.attack = ByteBuffer.wrap(data, offset + 8, 4).getInt();
        this.defense = ByteBuffer.wrap(data, offset + 12, 4).getInt();
        this.ammo = ByteBuffer.wrap(data, offset + 16, 4).getInt();
        this.isAlive = data[offset + 21] != 0;
        this.nameLength = ByteBuffer.wrap(data, offset + 22, 4).getInt();

        if(nameLength > MAX_NAME_LENGTH ) setNameLength(MAX_NAME_LENGTH);

        this.name = new String(data, offset + 26, getNameLength(), StandardCharsets.UTF_8);
    }

    @Override
    public DatagramPacket getDatagramPacket() {
        byte[] data = new byte[MAX_DATA_LENGTH];

        ByteBuffer buffer = ByteBuffer.wrap(data);
        byte[] nameBytes = getName().getBytes(StandardCharsets.UTF_8);

        if(nameLength > MAX_NAME_LENGTH ) setNameLength(MAX_NAME_LENGTH);
        System.arraycopy(nameBytes, 0, data, 26, this.nameLength);

        // Put the rest of the fields into the ByteBuffer
        buffer.putInt(0, getId());
        buffer.putInt(4, getHealth());
        buffer.putInt(8, getAttack());
        buffer.putInt(12, getDefense());
        buffer.putInt(16, getAmmo());
        buffer.put(21, (byte) (getIsAlive() ? 1 : 0));
        buffer.putInt(22, getNameLength());

        // Create and return the DatagramPacket
        return new DatagramPacket(data, data.length, this.getAddress(), this.getPort());
    }


    // Actions

    public void attack(Hero hero) {
        int damage = this.attack - hero.getDefense();
        if (damage > 0) {
            hero.takeDamage(damage);
            System.out.println(this.name + " attacks " + hero.getName() + " for " + damage + " damage.");
        } else {
            System.out.println(this.name + " attacks " + hero.getName() + " but does no damage.");
        }
    }

    public void defend() {
        this.defense += 10;
        System.out.println(this.name + " defends and gains 10 defense power.");
    }

    public void reload() {
        this.ammo = maxAmmo;
        System.out.println(this.name + " has reloaded.");
    }
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.isAlive = false;
            System.out.println(this.name + " has been defeated!");
        }
    }

    // getters and setters

    public int getId() { return Id; }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setAmmo(int ammo) { this.ammo = ammo; }
    public int getAmmo() { return ammo; }
    public void setMaxAmmo(int maxAmmo) { this.maxAmmo = maxAmmo; }
    public int getMaxAmmo() { return maxAmmo; }

    public int getNameLength(){ return nameLength; }

    public void setNameLength(int nameLength){ this.nameLength = nameLength; }
}

