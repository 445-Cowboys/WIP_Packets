package org.cowboys.packets;

import java.nio.ByteBuffer;

public class HeartbeatAck extends Packet {


    private final byte[] data;

    private final int opcode;

    public HeartbeatAck(ByteBuffer buffer) {

        //This code readies the bytebuffer data to be read
        int totalLength = buffer.limit();
        this.data = new byte[totalLength];
        buffer.get(data, 0, data.length);
        buffer.rewind();

        //This pulls the only byte of the array to the opcode constant
        this.opcode = buffer.get(0);
    }

    @Override
    public int getOpcode(){
        return -1;
    }
}
