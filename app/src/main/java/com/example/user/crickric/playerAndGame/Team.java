package com.example.user.crickric.playerAndGame;

import java.util.Arrays;

/**
 * Created by User on 15-Mar-18.
 */

public class Team {
    private String name;
    private Player players[];
    private int playerNumber;
    private int playerCount;
    private int openingBatsmanPosition;
    private int anotherOpeningBatsmanPosition;
    private int nextBatsmanPosition;
    private int nextBowlerPosition;

    public Team(String name) {
        this.name = name;
        this.playerCount=0;
    }

    public Team(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        players=new Player[playerNumber];
        playerCount=0;
    }

    public void addPlayer(String name){
        //System.out.println(playerCount+ "\t"+name);
        players[playerCount]=new Player(name);
        playerCount++;
    }

    public Player getPlayer(int index){
        //System.out.println(index+"\t"+players[index]);
        return players[index];
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

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
        this.players=new Player[playerNumber];
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getOpeningBatsmanPosition() {
        return openingBatsmanPosition;
    }

    public void setOpeningBatsmanPosition(int openingBatsmanPosition) {
        this.openingBatsmanPosition = openingBatsmanPosition;

    }

    public int getAnotherOpeningBatsmanPosition() {
        return anotherOpeningBatsmanPosition;
    }

    public void setAnotherOpeningBatsmanPosition(int anotherOpeningBatsmanPosition) {
        this.anotherOpeningBatsmanPosition = anotherOpeningBatsmanPosition;

    }

    public int getNextBatsmanPosition() {
        return nextBatsmanPosition;
    }

    public void setNextBatsmanPosition(int nextBatsmanPosition) {
        this.nextBatsmanPosition = nextBatsmanPosition;
    }

    public int getNextBowlerPosition() {
        return nextBowlerPosition;
    }

    public void setNextBowlerPosition(int nextBowlerPosition) {
        this.nextBowlerPosition = nextBowlerPosition;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", players=" + Arrays.toString(players) +
                ", playerNumber=" + playerNumber +
                ", playerCount=" + playerCount +
                '}';
    }
}
