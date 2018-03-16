package com.example.user.crickric.playerAndGame;

/**
 * Created by User on 15-Mar-18.
 */

public class Game {
    private String name;
    private Team firstTeam;
    private Team secondTeam;
    private int totalNoOfOver;
    private int noOfPlayerInOneTeam;

    public Game(String name, int totalNoOfOver, int noOfPlayerInOneTeam) {
        this.name = name;
        this.totalNoOfOver = totalNoOfOver;
        this.noOfPlayerInOneTeam = noOfPlayerInOneTeam;
    }

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
}
