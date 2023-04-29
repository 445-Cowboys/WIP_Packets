package org.cowboys.packets;

import java.nio.ByteBuffer;

public class MainTest {

    public static void main(String args[]){

        int[] playerHealth = {2, 3, 4};
        int[] playerAmmo = {8, 8, 8};
        int[] playerAbilityCD = {0, 1 ,0};

                Factory factory = new Factory();
        //factory.makeGameStatePacket(10, 6, playerHealth, playerAmmo,
        //playerAbilityCD, 1, "This is a test");

        GameState gamestate = new GameState(factory.makeGameStatePacket(10, 6, playerHealth, playerAmmo,
                playerAbilityCD, 1, "This is a test number 2"));

        gamestate.getBossHealth();
        gamestate.getBossAmmo();
        gamestate.getPlayerHealth();
        gamestate.getActionMessage();
    }
}
