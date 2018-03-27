package com.example.user.crickric.game;

/**
 * Created by User on 23-Mar-18.
 */

public interface Target {
    int getTargetRun();
    double requiredRunRate();
    int remainingBall();
    int requiredRun();
    boolean isBattingTeamWin();
}
