package com.example.user.crickric.ballAndOver;

/**
 * Created by User on 15-Mar-18.
 */

public class Ball {
    private int run;
    private BallType ballType;
    private OutType outType;
    private int legByeRun;

    public Ball(int run, BallType ballType, OutType outType, int legByeRun) {
        this.run = run;
        this.ballType = ballType;
        this.outType = outType;
        this.legByeRun = legByeRun;
    }

    public Ball() {}

    public static double convertToOver(int ball){
        return ((ball/6)+ (ball%6)*.1);
    }

    ///////////////
    /*
    getter and setter
    */
    //////////////

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }

    public OutType getOutType() {
        return outType;
    }

    public void setOutType(OutType outType) {
        this.outType = outType;
    }

    public int getLegByeRun() {
        return legByeRun;
    }

    public void setLegByeRun(int legByeRun) {
        this.legByeRun = legByeRun;
    }

}
