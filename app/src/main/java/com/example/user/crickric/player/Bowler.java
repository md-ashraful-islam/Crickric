package com.example.user.crickric.player;

import com.example.user.crickric.ball.Ball;

/**
 * Created by User on 22-Mar-18.
 */

public interface Bowler {
    int totalWicketTaken();
    int totalDots();
    int totalMeiden();
    int totalGivingRun();
    void addBowlingBall(Ball ball);
    void deleteLastBowlingBall();
    double getBowlingOverNumber();
}
