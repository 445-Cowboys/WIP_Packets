package org.cowboys;

import org.cowboys.packets.*;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        ByteBuffer buf = new Factory().makeEnterRoomPacket(5, 1231223);
        EnterRoom er = new EnterRoom(buf);
        System.out.println(er.getRoomNum() + " " + er.getPortNum());
    }
}