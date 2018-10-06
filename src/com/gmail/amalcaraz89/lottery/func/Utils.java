package com.gmail.amalcaraz89.lottery.func;

import java.util.List;

public final class Utils {

    public final static <T> boolean contains(List<T> list, T o) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(o)) {
                return true;
            }
        }
        return false;

    }

}
