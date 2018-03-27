package com.example.user.crickric.ball;

import com.example.user.crickric.over.OutType;

/**
 * Created by User on 15-Mar-18.
 */

public class Ball {
    private int run;
    private BallType ballType;
    private OutType outType;
    private int legByeRun;
    private int battingPlayerIndex;
    private int anotherPlayerIndex;
    private int bowlingPlayerIndex;

    public Ball(int run, BallType ballType, OutType outType, int legByeRun, int battingPlayerIndex, int anotherPlayerIndex, int bowlingPlayerIndex) {
        this.run = run;
        this.ballType = ballType;
        this.outType = outType;
        this.legByeRun = legByeRun;
        this.battingPlayerIndex = battingPlayerIndex;
        this.anotherPlayerIndex = anotherPlayerIndex;
        this.bowlingPlayerIndex = bowlingPlayerIndex;
    }

    public static double convertToOver(int ballNumber){
        return ((ballNumber/6)+ (ballNumber%6)*.1);
    }

    public int getTotalRun(){
        return getRun()+getExtraRun();
    }

    public int getExtraRun(){
        if(ballType==null || ballType==BallType.NOTHING || ballType==BallType.BOUNCER){
            return legByeRun;
        }
        return legByeRun+1;
    }

    public boolean isOut(){
        return !(outType == null || outType == OutType.NOTOUT);
    }

    public String getBallTextFormat(){
        String ballText="";
        if(outType!=null &&outType!=OutType.NOTOUT)
            ballText+="Wkt+";
        if(ballType==BallType.WIDE)
            ballText+="Wide+";
        else if(ballType==BallType.NO || ballType==BallType.NOANDWIDE)
            ballText+="No+";
        ballText+=run+"";
        if(legByeRun!=0)
            ballText+="+B"+legByeRun;
        return ballText+" ";
    }

    ///////////////getter and setter///////////////////////////////////////

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

    public int getBattingPlayerIndex() {
        return battingPlayerIndex;
    }

    public void setBattingPlayerIndex(int battingPlayerIndex) {
        this.battingPlayerIndex = battingPlayerIndex;
    }

    public int getBowlingPlayerIndex() {
        return bowlingPlayerIndex;
    }

    public void setBowlingPlayerIndex(int bowlingPlayerIndex) {
        this.bowlingPlayerIndex = bowlingPlayerIndex;
    }

    public int getAnotherPlayerIndex() {
        return anotherPlayerIndex;
    }

    public void setAnotherPlayerIndex(int anotherPlayerIndex) {
        this.anotherPlayerIndex = anotherPlayerIndex;
    }
}