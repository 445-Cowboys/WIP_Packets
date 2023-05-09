package org.cowboys.packets;

import java.nio.ByteBuffer;

public class PlayerCount extends Packet{
    private int playerCount;

    public PlayerCount(ByteBuffer buf){
        playerCount = buf.getInt(2);
    }

    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public int getOpcode() {
        return 10;
    }
}
