package com.example.user.crickric.ballAndOver;

/**
 * Created by User on 15-Mar-18.
 */

public enum OutType {
    NOTOUT(0),LBWOUT(1),HITOUT(2),BOLTOUT(3),CATCHOUT(4),STAMPINGOUT(5),RUNOUT(6);
    int value;

    OutType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
