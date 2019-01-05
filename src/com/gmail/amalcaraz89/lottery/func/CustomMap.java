package com.gmail.amalcaraz89.lottery.func;

import java.util.HashMap;

// TODO: Think in better solution
public class CustomMap<K, V> extends HashMap<K,V> {

    @Override
    public String toString() {

        String output = super.toString();
        return output.replaceAll("(, |\\{)([^ ]+?)=", "$1\"$2\": ");

    }
}
