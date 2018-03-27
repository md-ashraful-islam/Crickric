package com.example.user.crickric.over;

import com.example.user.crickric.ball.Ball;
import com.example.user.crickric.ball.BallType;

import java.util.LinkedList;


/**
 * Created by User on 15-Mar-18.
 */

public class Over {
    private LinkedList<Ball> balls;
    private int totalWicket;
    private int dots;
    private int run;
    private int extraRun;
    private int totalRun;

    public Over() {
        initialize();
    }

    public void initialize(){
        balls=new LinkedList<>();
        totalWicket=0;
        dots=0;
        run=0;
        extraRun=0;
        totalRun=0;
    }

    public Ball getLastBall(){
        return balls.getLast();
    }

    public boolean isFull(){
        return getBallCounter()==6;
    }

    public boolean isEmpty(){
        return balls.size()==0;
    }

    public void addBall(Ball ball){
        balls.add(ball);
        if(ball.getTotalRun()!=0){
            run+=ball.getRun();
            extraRun+=ball.getExtraRun();
            totalRun+=ball.getTotalRun();
        }
        else
            dots++;
        if(ball.isOut())
            totalWicket++;
    }

    public void deleteLastBall(){
        Ball ball=balls.getLast();
        balls.removeLast();
        if(ball.getTotalRun()!=0){
            run-=ball.getRun();
            extraRun-=ball.getExtraRun();
            totalRun-=ball.getTotalRun();
        }
        else
            dots--;
        if(ball.isOut())
            totalWicket--;
    }

    public static int convertToBall(double overNumber){
        return (int)(((int)overNumber*6)+(overNumber-(int)overNumber)*10);
    }

    public int getBallCounter() {
        int ballCounter=0;
        for(Ball x:balls){
            if(x.getBallType()==null || x.getBallType()== BallType.NOTHING || x.getBallType()==BallType.BOUNCER)
                ballCounter++;
        }
        return ballCounter;
    }

    public static double getOverNumber(LinkedList<Over> overs){
        if(overs.size()==0)
            return 0;
        if(overs.getLast().isFull())
            return overs.size();
        return ((overs.size() - 1) + (overs.getLast().getBallCounter() * .1));
    }

    /////////////////getter////////////////////////////////////////////

    public int getTotalWicket() {
        return totalWicket;
    }

    public int getDots() {
        return dots;
    }

    public boolean isMedein() {
        return (getTotalRun()==0 && balls.size()==6);
    }

    public int getRun() {
        return run;
    }

    public int getExtraRun() {
        return extraRun;
    }

    public LinkedList<Ball> getBalls() {
        return balls;
    }

    public int getTotalRun() {
        return totalRun;
    }
}