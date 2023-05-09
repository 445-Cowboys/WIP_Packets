package org.cowboys;

import org.cowboys.packets.*;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        long x = System.nanoTime();
        ByteBuffer buf = new Factory().makeGameRoomsUpdate(new int[]{1,2,3}, new boolean[]{false,true,true}, new int[]{0,1,2}, new int[]{0,1,1}, x);
        GameRoomsUpdate er = new GameRoomsUpdate(buf);
        System.out.println(er);
    }
}