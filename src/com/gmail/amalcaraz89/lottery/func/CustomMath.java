package com.gmail.amalcaraz89.lottery.func;

import io.nuls.contract.sdk.Block;
import io.nuls.contract.sdk.Msg;

public class CustomMath {

    public static float random(long seed) {
        int hash1 = Block.currentBlockHeader().getPackingAddress().toString().substring(2).hashCode();
        int hash2 = Msg.address().toString().substring(2).hashCode();
        int hash3 = Msg.sender() != null ? Msg.sender().toString().substring(2).hashCode() : 0;
        int hash4 = Long.valueOf(Block.timestamp()).toString().hashCode();

        long hash = seed ^ hash1 ^ hash2 ^ hash3 ^ hash4;

        seed = (hash * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return ((int) (seed >>> 24) / (float) (1 << 24));
    }

    public static float random() {
        return random(0x5DEECE66DL);
    }

}
