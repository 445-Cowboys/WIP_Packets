package org.cowboys.packets;

import java.nio.ByteBuffer;

public class PlayerAction {

    /*

    02 <Game room number> | <Action type> | <player you heal if action is type 3 and your attribute is a healer>

    */

    private final int gameRoom;

    private final int action;

    private final int heal;
    private final byte[] data;

    public PlayerAction(ByteBuffer buffer) {
        int totalLength = buffer.limit();
            this.data = new byte[totalLength];
            buffer.get(data, 0, data.length);
            buffer.rewind();
            this.gameRoom = buffer.getInt(1);
            this.action = buffer.getInt(6);

            //If not a healer this should always be 0,
            //if using healer ability the player number will be listed.
            this.heal = buffer.getInt(11);
    }

    

}
