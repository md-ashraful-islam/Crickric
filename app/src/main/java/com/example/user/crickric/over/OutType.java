package com.example.user.crickric.over;

/**
 * Created by User on 15-Mar-18.
 */

public enum OutType {
    RUNOUT(0),LBWOUT(1),HITOUT(2),BOLTOUT(3),CATCHOUT(4),STAMPING(5),NOTOUT(6);
    int value;

    OutType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
