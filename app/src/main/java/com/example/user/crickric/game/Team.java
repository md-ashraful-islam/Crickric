package com.example.user.crickric.game;

import com.example.user.crickric.player.Player;

import java.util.Arrays;

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

    public Team(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        initialize();
    }

    public void initialize(){
        players=new Player[playerNumber];
        playerCount=0;
    }

    public void initializePlayer(){
        for(int i=0;i<playerNumber;i++){
            players[i].initialize();
        }
    }

    public void addPlayer(String name){
        //System.out.println(playerCount+ "\t"+name);
        players[playerCount]=new Player(name);
        playerCount++;
    }

    public Player getPlayer(int index) {
        //System.out.println(index+"\t"+players[index]);
        return players[index];
    }

    ///////////////getter and setter//////////////

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
