package org.cowboys;

import org.cowboys.packets.Factory;
import org.cowboys.packets.GameState;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        ByteBuffer buf = new Factory().makeGameStatePacket(1,2,new int[]{1,2,3},new int[]{1,2,3},new int[]{1,2,3},12,"Because tonight will be the night that I will fall for youuuu");
        GameState gs = new GameState(buf);
        System.out.println("as");
    }
}