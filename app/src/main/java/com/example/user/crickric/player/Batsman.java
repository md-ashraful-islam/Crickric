package com.example.user.crickric.player;

import com.example.user.crickric.ball.Ball;

/**
 * Created by User on 22-Mar-18.
 */

public interface Batsman {
    int totalRun();
    int totalBallPlayed();
    int totalFours();
    int totalSixes();
    double strikeRate();
    void addPlayedBall(Ball ball);
    void deletePlayedLastBall(int run);
}
