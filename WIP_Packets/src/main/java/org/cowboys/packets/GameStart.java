package org.cowboys.packets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;

public class GameStart extends Packet {

    /*
    <opcode = 04> 0 <character num> 0 <boss num> 0 <aead key>
    */

    private final byte[] data;
    private final int character;
    private final SecretKey symmetricKey;

    public GameStart(ByteBuffer buffer){

        //This code readies the bytebuffer data to be read
        int totalLength = buffer.limit();
        System.out.println((totalLength));
        this.data = new byte[totalLength];
        buffer.get(data, 0, data.length);
        buffer.rewind();
        int offset = 2;

        //Start pulling data
        this.character = buffer.getInt(offset);
        offset += 2;
        //byte[] symmetricKeyBytes = new byte[totalLength - offset];
        byte[] symmetricKeyBytes = new byte[8];
        buffer.get(symmetricKeyBytes);

        // Convert the symmetric key bytes back into a SecretKey object
        symmetricKey = new SecretKeySpec(symmetricKeyBytes, "AES");
    }
    @Override
    public int getOpcode(){
        return 4;
    }

    public int getCharacter(){
        return character;
    }

    public SecretKey getSymmetricKey(){
        return symmetricKey;
    }
}
