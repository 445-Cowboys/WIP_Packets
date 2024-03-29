package org.cowboys.charTemplates;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class AckPacket extends PacketFactory {

    //Packet Opcode
    public static final int TYPE = 4;

    //Block number of packet
    private int blockNumber;

    public AckPacket(InetAddress address, int port, int type, int blockNumber) {
        super(address, port, type);

        //set the block number that the packet will acknowledge
        this.blockNumber = blockNumber;
    }

    public AckPacket(DatagramPacket packet, int type) {
        super(packet, type);

        byte[] data = packet.getData();

        //start 2 bytes after the opcode and get the block number
        this.blockNumber = (((data[2] & 0xff) << 8) | (data[3] & 0xff));
    }

    @Override
    public DatagramPacket getDatagramPacket() {
        byte[] data = new byte[4];

        data[0] = 0;
        data[1] = (byte)this.getType();
        data[2] = (byte)((this.blockNumber & 0xffff) >> 8);
        data[3] = (byte)(this.blockNumber & 0xff);

        return new DatagramPacket(data, data.length, this.getAddress(), this.getPort());
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }
    public int getType() { return TYPE; }

}
