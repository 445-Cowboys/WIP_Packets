

package org.cowboys.packets;

import com.csc445cowboys.guiwip.Net;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;

public class GameStart extends Packet {

    /*
    <opcode = 04> 0 <character num> 0 <boss num> 0 <aead key>
    */

    private final byte[] data;
    private final int character;
    private final int bossNum;
    private final int gameRoom;
    private final byte[] symmetricKey;

    public GameStart(ByteBuffer buffer){

        AEAD aead = new AEAD;

        //This code readies the bytebuffer data to be read
        int totalLength = buffer.limit();
        System.out.println((totalLength));
        this.data = new byte[totalLength];
        buffer.get(data, 0, data.length);
        buffer.rewind();
        int offset = 2;

        //Start pulling data
        this.character = buffer.getInt(offset);
        offset += 5;
        this.bossNum = buffer.getInt(offset);
        offset += 5;
        this.gameRoom = buffer.getInt(offset);
        offset += 5;
        symmetricKey = new byte[totalLength - offset];
        buffer.position(offset);
        buffer.get(symmetricKey, 0, symmetricKey.length);
        aead.parseKey(symmetricKey);


        // Convert the symmetric key bytes back into a SecretKey object
        //symmetricKey = new SecretKeySpec(symmetricKeyBytes, "AES");
    }
    @Override
    public int getOpcode(){
        return 4;
    }
    public int getCharacter(){
        return character;
    }
    public int getBossNum() { return bossNum;}
    public int getGameRoom() { return gameRoom; }
    public AEAD getSymmetricKey(){
        return aead;
    }
}
