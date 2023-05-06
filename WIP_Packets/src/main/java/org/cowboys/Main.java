package org.cowboys;

import org.cowboys.packets.EnterRoom;
import org.cowboys.packets.Factory;
import org.cowboys.packets.GameState;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        ByteBuffer buf = new Factory().makeEnterRoomPacket(5, 1231223, "quandale dingle");
        EnterRoom er = new EnterRoom(buf);
        System.out.println(er);
    }
}