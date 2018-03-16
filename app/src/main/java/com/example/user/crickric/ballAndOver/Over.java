package com.example.user.crickric.ballAndOver;

/**
 * Created by User on 15-Mar-18.
 */

public class Over {
    private Ball balls[];
    private int ballCounter;

    public Over() {
        balls=new Ball[6];
        ballCounter=0;
    }

    public int getTotalRunInOver(){
        int run=0;
        for(Ball x:balls){
            run+=x.getRun();
        }
        return run;
    }

    public boolean isFull(){
        return ballCounter==6;
    }

    public void addBall(Ball ball){
        addBall(ball,ballCounter);
    }
    public void deleteLastBall(){
        deleteBall(ballCounter);
    }
    public void deleteBall(int ballCount){
        balls[ballCount]=null;
        ballCounter--;
    }
    public void addBall(Ball ball,int ballCount){
        this.balls[ballCount]=ball;
        ballCounter++;
    }
    public static int convertToBall(double over){
        return (int)(((int)over*6)+(over-(int)over)*10);
    }

    ///////////////
    /*
    getter and setter
    */
    //////////////

    public int getBallCounter() {
        return ballCounter;
    }

}
