package com.example.user.crickric.ball;

/**
 * Created by User on 15-Mar-18.
 */

public enum BallType {
    BOUNCER(0),WIDE(1),NO(2),NOANDWIDE(3),NOTHING(4);
    int value;
    BallType(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
