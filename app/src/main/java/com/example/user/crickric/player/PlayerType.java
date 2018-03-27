package com.example.user.crickric.player;

/**
 * Created by User on 15-Mar-18.
 */

public enum PlayerType {
    BATSMAN(0),BOWLER(1),ALLRONDER(2),WICKETKIPER(3),BATSMAN_WICKETKIPER(4);
    int value;

    PlayerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
