package com.example.user.crickric.playerAndGame;

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
    
    private static volatile Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private Game() {}

    ///////////////
    /*
    getter and setter
    */
    //////////////

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
}
