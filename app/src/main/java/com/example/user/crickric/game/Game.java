package com.example.user.crickric.game;

import com.example.user.crickric.ball.Ball;

/**
 * Created by User on 18-Mar-18.
 */

public class Game {
    private String name;
    private Team firstTeam;
    private Team secondTeam;
    private int totalNoOfOver;
    private int noOfPlayerInOneTeam;
    private boolean firstTeamBatting;
    private boolean firstInning;
    private FirstInnings firstInnings;
    private SecondInnings secondInnings;
    
    private static volatile Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private Game() {
        //initialize();
    }

    public void initialize(){
        firstInning=true;
        firstInnings=new FirstInnings();
        secondInnings=new SecondInnings();
    }

    ///for adding new ball /////////////////////////////////////
    public void addBall(Ball ball){
        if(firstInning)
            firstInnings.addBall(ball);
        else
            secondInnings.addBall(ball);
    }

    //for undoing///////////////////////////////////////////////
    public void deleteLastBall(){
        if(firstInning)
            firstInnings.deleteLastBall();
        else
            secondInnings.deleteLastBall();
    }

    public String getGameStatus(){
        if(firstInning)
            return getFirstInningsText();
        return getSecondInningsText();
    }
    public String getFirstInningsText(){
        return firstInnings.getBattingTeam().getName()+"-"+firstInnings.getTotalRun()+"/"+firstInnings.getTotalWicket()+"  Over-"+firstInnings.getPlayedOver()+"\n"
                +firstInnings.getBowlingTeam().getName();
    }

    public String getSecondInningsText(){
        return secondInnings.getBattingTeam().getName()+"-"+secondInnings.getTotalRun()+"/"+secondInnings.getTotalWicket()+"  Over-"+secondInnings.getPlayedOver()+"\n"
                +secondInnings.getBowlingTeam().getName()+ "-"+firstInnings.getTotalRun()+"/"+firstInnings.getTotalWicket()+"  Over-"+firstInnings.getPlayedOver();
    }

    public Ball getLastBall(){
        return getInnings().getLastBall();
    }

    ////////////////////getter and setter//////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        firstTeam.setPlayerNumber(noOfPlayerInOneTeam);
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        secondTeam.setPlayerNumber(noOfPlayerInOneTeam);
        this.secondTeam = secondTeam;
    }

    public int getTotalNoOfOver() {
        return totalNoOfOver;
    }

    public void setTotalNoOfOver(int totalNoOfOver) {
        this.totalNoOfOver = totalNoOfOver;
    }

    public int getNoOfPlayerInOneTeam() {
        return noOfPlayerInOneTeam;
    }

    public void setNoOfPlayerInOneTeam(int noOfPlayerInOneTeam) {
        this.noOfPlayerInOneTeam = noOfPlayerInOneTeam;
    }

    public boolean isFirstTeamBatting() {
        return firstTeamBatting;
    }

    public void setFirstTeamBatting(boolean firstTeamBatting) {
        this.firstTeamBatting = firstTeamBatting;
    }

    public boolean isFirstInnings() {
        return firstInning;
    }

    public void setFirstInnings(boolean firstInnings) {
        this.firstInning = firstInnings;
    }

    public FirstInnings getInnings() {
        if(firstInning)
            return firstInnings;
        return secondInnings;
    }

    public FirstInnings getFirstInnings() {
        return firstInnings;
    }

    public SecondInnings getSecondInnings() {
        return secondInnings;
    }
}
