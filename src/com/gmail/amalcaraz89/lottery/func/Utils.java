package com.gmail.amalcaraz89.lottery.func;

import io.nuls.contract.sdk.Address;

import java.util.List;

public final class Utils {

    public static boolean containsAddress(List<Address> list, Address o) {

        for (int i = 0; i< list.size(); i++) {
            if (list.get(i) == o) {
                return true;
            }
        }
        return false;

    }

}
