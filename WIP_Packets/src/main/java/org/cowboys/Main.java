package org.cowboys;

import org.cowboys.packets.*;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        ByteBuffer buf = new Factory().makeGameRooms(new int[]{0,0,0}, new boolean[]{false,false,false}, new int[]{1,1,1}, new int[]{1,1,1}, 57);
        GameRooms er = new GameRooms(buf);
        System.out.println(er);
    }
}