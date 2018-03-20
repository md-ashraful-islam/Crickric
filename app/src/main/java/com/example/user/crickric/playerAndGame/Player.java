package com.example.user.crickric.playerAndGame;


import com.example.user.crickric.ballAndOver.Ball;
import com.example.user.crickric.ballAndOver.Over;

import java.util.LinkedList;

/**
 * Created by User on 15-Mar-18.
 */

public class Player {
    private PlayerType playerType;
    private String name;
    private int run;
    private int positionOfBatting;
    private LinkedList<Over> bowlingOver;
    private int wicketTaken;
    private int givingRun;
    private boolean batted;
    private boolean insured;
    private int totalBallPlayed;

    public Player(PlayerType playerType, String name) {
        this(name);
        this.playerType = playerType;
    }

    public Player(String name) {
        this.name = name;
        run=0;
        wicketTaken=0;
        playerType=PlayerType.BATSMAN;
        positionOfBatting=0;
        bowlingOver=new LinkedList<>();
        givingRun=0;
        batted=false;
        insured=false;
        totalBallPlayed=0;
    }

    public void deleteLastBall(){
        bowlingOver.getLast().deleteLastBall();
    }
    public void deleteBall(int ballCount){
        bowlingOver.getLast().deleteBall(ballCount);
    }
    public void addBall(Ball ball, int ballCount){
        if(bowlingOver.size()==0){
            addNewOver();
        }
        else if(bowlingOver.getLast().isFull()){
            addNewOver();
        }
        bowlingOver.getLast().addBall(ball,ballCount);
    }

    public void addBall(Ball ball){
        addBall(ball,bowlingOver.getLast().getBallCounter());
    }

    public void addNewOver(){
        bowlingOver.add(new Over());
    }

    public void addOver(Over over){
        bowlingOver.add(over);
        addGivingRun(over.getTotalRunInOver());
    }

    public void addGivingRun(int run){
        givingRun+=run;
    }

    public void addRun(int run){
        this.run+=run;
        totalBallPlayed++;
    }

    public void increamentWicketTaken(){
        wicketTaken++;
    }

    ///////////////
    /*
    getter and setter
    */
    //////////////

    public int getWicketTaken() {
        return wicketTaken;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getPositionOfBatting() {
        return positionOfBatting;
    }

    public void setPositionOfBatting(int positionOfBatting) {
        this.positionOfBatting = positionOfBatting;
    }

    public int getGivingRun() {
        return givingRun;
    }

    public void setGivingRun(int givingRun) {
        this.givingRun = givingRun;
    }

    public boolean isBatted() {
        return batted;
    }

    public void setBatted(boolean batted) {
        this.batted = batted;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public int getTotalBallPlayed() {
        return totalBallPlayed;
    }

    public void setTotalBallPlayed(int totalBallPlayed) {
        this.totalBallPlayed = totalBallPlayed;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerType=" + playerType +
                ", name='" + name + '\'' +
                ", run=" + run +
                ", positionOfBatting=" + positionOfBatting +
                ", bowlingOver=" + bowlingOver +
                ", wicketTaken=" + wicketTaken +
                ", givingRun=" + givingRun +
                ", batted=" + batted +
                ", insured=" + insured +
                ", totalBallPlayed=" + totalBallPlayed +
                '}';
    }
}
