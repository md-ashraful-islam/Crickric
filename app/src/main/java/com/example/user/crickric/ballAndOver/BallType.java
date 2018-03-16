package com.example.user.crickric.ballAndOver;

/**
 * Created by User on 15-Mar-18.
 */

public enum BallType {
    NOTHING(0),WIDE(1),NO(2),NOWIDE(3),BOUNCER(4);
    int value;
    BallType(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
