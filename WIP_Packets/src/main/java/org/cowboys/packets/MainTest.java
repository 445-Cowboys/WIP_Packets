package org.cowboys.packets;

import java.nio.ByteBuffer;

public class MainTest {

    public static void main(String args[]){




        /********************************
         * Test Enter Room packet
         *
        ByteBuffer roomBuf = new Factory().makeEnterRoomPacket(1, "Your usename is ...");
        EnterRoom er = new EnterRoom(roomBuf);
        System.out.println(er.getOpcode());
        System.out.println(er.getRoomNum());
        System.out.println(er.getuserName());
        */

        /********************************
         * Test Player Action packet
         *

        ByteBuffer actionBuf = new Factory().makePlayerActionPacket(1, 2, 3);
        PlayerAction pa = new PlayerAction(actionBuf);
        System.out.println(pa.getOpcode());
        System.out.println(pa.getGameRoom());
        System.out.println(pa.getAction());
        System.out.println(pa.getHeal());

        */

        /********************************
         * Test Game State packet
         *
        int[] playerHealth = {2, 3, 4};
        int[] playerAmmo = {8, 8, 8};
        int[] playerAbilityCD = {0, 1 ,0};

                Factory factory = new Factory();
        //factory.makeGameStatePacket(10, 6, playerHealth, playerAmmo,
        //playerAbilityCD, 1, "This is a test");

        GameState gamestate = new GameState(factory.makeGameStatePacket(10, 6, playerHealth, playerAmmo,
                playerAbilityCD, 1, 1, "This is a test"));

        System.out.println(gamestate.getBossHealth());
        System.out.println(gamestate.getBossAmmo());
        System.out.println(gamestate.getPlayerHealth(1));
        System.out.println(gamestate.getCurrentPlayer());
        System.out.println(gamestate.getBlockNum());
        System.out.println(gamestate.getActionMessage());
         */


    }

}
