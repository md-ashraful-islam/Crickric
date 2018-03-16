package com.example.user.crickric.playerAndGame;

/**
 * Created by User on 15-Mar-18.
 */

public class Team {
    private String name;
    private Player players[];
    private int playerNumber;
    private int playerCount;

    public Team(String name) {
        this.name = name;
        this.playerCount=0;
    }
    public void addPlayer(Player player){
        players[playerCount]=player;
        playerCount++;
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
}
