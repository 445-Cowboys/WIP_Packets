package org.cowboys.packets;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Factory {

    //cast a given int's value to a two byte array
    private byte[] getIntBytes(int x){
        return new byte[]{
                (byte) (x >> 8),
                (byte) x
        };
    }

    public static int bytesToInt(byte[] intBuf) {
        return ByteBuffer.wrap(new byte[]{
                0x00,
                0x00,
                intBuf[1],
                intBuf[0]
        }).getInt();
    }

        public ByteBuffer makeGameStatePacket(int bossHealth, int bossAmmo, int[] playerHealth, int[] playerAmmo,
        int[] playerAbilityCD, int currentPlayer, String actionMessage) {

            // Build byte buffer stream
            byte[] actionMessageBytes = actionMessage.getBytes();
            int messageLength = actionMessageBytes.length;
            ByteBuffer buffer = ByteBuffer.allocate(62 + messageLength); // Total length of packet is 62 bytes
            buffer.put((byte) 0x09);
            buffer.putInt(bossHealth);
            buffer.put((byte) 0);
            buffer.putInt(bossAmmo);
            buffer.put((byte) 0);
            for (int i = 0; i < 3; i++) {
                buffer.putInt(playerHealth[i]);
                buffer.put((byte) 0);
                buffer.putInt(playerAmmo[i]);
                buffer.put((byte) 0);
                buffer.putInt(playerAbilityCD[i]);
                buffer.put((byte) 0);
            }
            buffer.putInt(currentPlayer);
            buffer.put((byte) 0);
            buffer.put(actionMessageBytes);
            buffer.flip();
            return buffer;
        }

    }
