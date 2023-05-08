package org.cowboys;

import org.cowboys.packets.*;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        ByteBuffer buf = new Factory().makeEnterRoomPacket(5, 1231223);
        EnterRoom er = new EnterRoom(buf);
        System.out.println(er.getRoomNum() + " " + er.getPortNum());
=======
        ByteBuffer buf = new Factory().makeGameRooms(new int[]{0,0,0}, new boolean[]{false,false,false}, new int[]{1,1,1}, new int[]{1,1,1}, 57);
        GameRooms er = new GameRooms(buf);
        System.out.println(er);
>>>>>>> 7db91b7a776e0d0615af88bbb0f19f641c20f18d
    }
}