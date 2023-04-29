package org.cowboys.packets;

import java.nio.ByteBuffer;

public class GameState extends Packet{

    /*
    09 <Boss Health> 0 <Boss Ammo> 0
    <Player 1 Health> 0 <Player 1 Ammo> 0 < Player 1 Ability CD> 0
    <Player 2 Health> 0 <Player 2 Ammo> 0 < Player 2 Ability CD> 0
    <Player 3 Health> 0 <Player 3 Ammo> 0 < Player 3 Ability CD> 0
    <Player whose turn it is> 0 <Action message>
     */

    private final int bossHealth;
    private final int bossAmmo;
    private final int[] playerHealth;
    private final int[] playerAmmo;
    private final int[] playerAbilityCD;
    private final int currentPlayer;
    private final String actionMessage;
    private final byte[] data;

    public GameState(ByteBuffer buffer) {
    int totalLength = buffer.limit();
        this.data = new byte[totalLength];
        buffer.get(data, 0, data.length);
        this.bossHealth = buffer.getInt(1);
        this.bossAmmo = buffer.getInt(6);
        this.playerHealth = new int[3];
        this.playerAmmo = new int[3];
        this.playerAbilityCD = new int[3];
    int offset = 11;
        for (int i = 0; i < 3; i++) {
        this.playerHealth[i] = buffer.getInt(offset);
        offset += 5;
        this.playerAmmo[i] = buffer.getInt(offset);
        offset += 5;
        this.playerAbilityCD[i] = buffer.getInt(offset);
        offset += 5;
    }
        this.currentPlayer = buffer.getInt(offset);
    offset += 5;
    System.out.println(offset + " " + totalLength);
    byte[] actionMessageBytes = new byte[totalLength - offset - 1];
        buffer.get(actionMessageBytes, 0, actionMessageBytes.length);
        this.actionMessage = new String(actionMessageBytes);
}

    @Override
    public int getOpcode(){
        return 9;
    }

    public int getBossHealth() {
        return bossHealth;
    }

    public int getBossAmmo() {
        return bossAmmo;
    }

    public int[] getPlayerHealth() {
        return playerHealth;
    }

    public int[] getPlayerAmmo() {
        return playerAmmo;
    }

    public int[] getPlayerAbilityCD() {
        return playerAbilityCD;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public String getActionMessage() {
        return actionMessage;
    }



}
