package com.example.user.crickric.player;

import android.annotation.SuppressLint;

import com.example.user.crickric.ball.Ball;
import com.example.user.crickric.over.OutType;
import com.example.user.crickric.over.Over;

import java.util.Formatter;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by User on 15-Mar-18.
 */

public class Player implements Batsman,Bowler {

    /////////////////////////for Bowler/////////////////////////
    private LinkedList<Over> ballingOvers;
    ////////////////////////////////////////////////////////////

    /////////////////////////for batsman///////////////////////
    private int run;
    private int fours;
    private int sixes;
    private int noOfBallPlayed;
    ///////////////////////////////////////////////////////////

    private PlayerType playerType;
    private String name;
    private boolean batted;
    private boolean insured;
    private OutType outType;

    public Player(String name) {
        this.name = name;
        initialize();
    }

    public void initialize(){
        /////bowler/////////
        ballingOvers=new LinkedList<>();
        /////////////////////
        //////batsman//////////
        run=0;
        fours=0;
        sixes=0;
        noOfBallPlayed=0;
        ///////////////

        playerType=PlayerType.BATSMAN;
        batted=false;
        insured=false;
        outType=OutType.NOTOUT;
    }

    ////bowler's method/////////////////////////////////////////

    @Override
    public int totalWicketTaken() {
        int totalWicket=0;
        for(Over over:ballingOvers){
            totalWicket+=over.getTotalWicket();
        }
        return totalWicket;
    }

    @Override
    public int totalDots() {
        int totalDots=0;
        for(Over over:ballingOvers){
            totalDots+=over.getDots();
        }
        return totalDots;
    }

    @Override
    public int totalMeiden() {
        int totalmeidein=0;
        for(Over over:ballingOvers){
            if(over.isMedein())
                totalmeidein++;
        }
        return totalmeidein;
    }

    @Override
    public int totalGivingRun() {
        int totalGivingRun=0;
        for(Over over:ballingOvers){
            totalGivingRun+=over.getTotalRun();
        }
        return totalGivingRun;
    }

    //for adding a new ball
    @Override
    public void addBowlingBall(Ball ball){
        System.out.println("PP-"+ballingOvers.size());
        if(ballingOvers.size()==0||ballingOvers.getLast().isFull())
            ballingOvers.add(new Over());
        ballingOvers.getLast().addBall(ball);
    }

    //for undo last ball
    @Override
    public void deleteLastBowlingBall() {
        ballingOvers.getLast().deleteLastBall();
        if(ballingOvers.getLast().isEmpty())
            ballingOvers.removeLast();
    }

    @Override
    public double getBowlingOverNumber(){
        return Over.getOverNumber(ballingOvers);
    }

    /////////////////////////////////////////////////////////////

    //////////batsman's method///////////////////////////////////

    @Override
    public int totalRun() {
        return run;
    }

    @Override
    public int totalBallPlayed() {
        return noOfBallPlayed;
    }

    @Override
    public int totalFours() {
        return fours;
    }

    @Override
    public int totalSixes() {
        return sixes;
    }

    @Override
    public double strikeRate() {
        if(noOfBallPlayed!=0)
            return (100.0*run)/noOfBallPlayed;
        return 0;
    }

    @Override
    public void addPlayedBall(Ball ball) {
        int run=ball.getRun();
        this.run+=run;
        if(run==4)
            fours++;
        else if(run==6)
            sixes++;
        noOfBallPlayed++;
    }

    @Override
    public void deletePlayedLastBall(int run) {
        this.run-=run;
        if(run==4)
            fours--;
        else if(run==6)
            sixes--;
        noOfBallPlayed--;

    }

    ////////////////////////////////////////////////////////////////////


    public String getBatsmanTextFormat(){
        StringBuilder sb=new StringBuilder();
        Formatter formatter=new Formatter(sb, Locale.US);
        return String.valueOf(formatter.format("%-14s%3d(%3d)%7d%7d%10.2f",name,totalRun(),totalBallPlayed(),totalFours(),totalSixes(),(float)strikeRate()));
        //Batsman1       100(100)     10      10    100.00
        //return (totalRun()+"("+totalBallPlayed()+")"+""+totalFours()+""+totalSixes()+""+strikeRate());
    }

    public String getBowlerTextFormat(){
        StringBuilder sb=new StringBuilder();
        Formatter formatter=new Formatter(sb, Locale.US);
        return String.valueOf(formatter.format("%-20s%.1f%11d%11d%8d",name,getBowlingOverNumber(),totalMeiden(),totalGivingRun(),totalWicketTaken()));
        //Bowler                  100         10        100      10
    }

    /////////////getter and setter//////////////////////////////////////
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

    public OutType getOutType() {
        return outType;
    }

    public void setOutType(OutType outType) {
        this.outType = outType;
    }

    @Override
    public String toString() {
        return "Player{" +
                "ballingOvers=" + ballingOvers +
                ", run=" + run +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", noOfBallPlayed=" + noOfBallPlayed +
                ", playerType=" + playerType +
                ", name='" + name + '\'' +
                ", batted=" + batted +
                ", insured=" + insured +
                ", outType=" + outType +
                '}';
    }
}
