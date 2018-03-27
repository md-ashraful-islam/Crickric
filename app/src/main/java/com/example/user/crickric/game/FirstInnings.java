package com.example.user.crickric.game;

import com.example.user.crickric.ball.Ball;
import com.example.user.crickric.over.OutType;
import com.example.user.crickric.over.Over;
import com.example.user.crickric.player.Player;

import java.util.Formatter;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by User on 21-Mar-18.
 */

public class FirstInnings {
    private Team battingTeam;
    private Team  bowlingTeam;
    private LinkedList<Over> overs;
    private int totalRun;
    private int totalWicket;
    private int battingBatsmanPosition;
    private int anotherBatsmanPosition;
    private int nextBatsmanPosition;
    private int outBatsmanPosition;
    private int nextBattingBatsmanPosition;
    private int currentBowlerPosition;
    private int nextBowlerPosition;
    private LinkedList<Integer> partnership;
    private int currentPartnerShip;
    private int lastWicketBatsmanIndex;
    private LinkedList<Integer> outtedBatsmanIndex;

    public FirstInnings() {
        initialize();
    }

    public void initialize(){
        overs=new LinkedList<>();
        totalRun=0;
        totalWicket=0;
        currentPartnerShip=0;
        partnership=new LinkedList<>();
        lastWicketBatsmanIndex=-1;
        outtedBatsmanIndex=new LinkedList<>();
    }

    public boolean isFinishOver(){
        return Game.getInstance().getTotalNoOfOver()==overs.size()&& overs.getLast().isFull();
    }

    public void addNewOver(){
        overs.add(new Over());
    }

    public void deleteOver(){
        overs.removeLast();
    }

    public void swapPlayerPosition(){
        battingBatsmanPosition^=anotherBatsmanPosition;
        anotherBatsmanPosition^=battingBatsmanPosition;
        battingBatsmanPosition^=anotherBatsmanPosition;
    }

    public void setBatsmanPosition(){
        if(outBatsmanPosition==battingBatsmanPosition)
            battingBatsmanPosition=nextBatsmanPosition;
        else
            anotherBatsmanPosition=nextBatsmanPosition;
        if(battingBatsmanPosition!=nextBattingBatsmanPosition)
            swapPlayerPosition();
    }

    public Ball getLastBall(){
        return overs.getLast().getLastBall();
    }

    //for adding new ball/////////////////////////////
    public void addBall(Ball ball){
        if(overs.size()==0||overs.getLast().isFull()){
            addNewOver();
            currentBowlerPosition=nextBowlerPosition;
        }
        totalRun+=ball.getTotalRun();
        overs.getLast().addBall(ball);
        currentPartnerShip+=ball.getTotalRun();
        battingTeam.getPlayer(battingBatsmanPosition).addPlayedBall(ball);
        bowlingTeam.getPlayer(currentBowlerPosition).addBowlingBall(ball);
        if((ball.getRun()+ball.getLegByeRun())%2==1)
            swapPlayerPosition();
        if(ball.isOut()){
            if(ball.getOutType()!= OutType.RUNOUT && ball.getOutType()!=OutType.HITOUT){
                outBatsmanPosition=battingBatsmanPosition;
                battingTeam.getPlayer(outBatsmanPosition).setOutType(ball.getOutType());
            }
            totalWicket++;
            partnership.add(currentPartnerShip);
            currentPartnerShip=0;
        }
    }

    public void setPlayerPosition(){
        setBatsmanPosition();
        lastWicketBatsmanIndex=outBatsmanPosition;
        outtedBatsmanIndex.add(lastWicketBatsmanIndex);
    }

    ///for undoing////////////////////////////////
    public void deleteLastBall(){
        if(overs.size()!=0 && !overs.getLast().isEmpty()){
            Ball ball=overs.getLast().getLastBall();
            totalRun-=ball.getTotalRun();
            currentPartnerShip-=ball.getTotalRun();
            overs.getLast().deleteLastBall();
            battingBatsmanPosition=ball.getBattingPlayerIndex();
            anotherBatsmanPosition=ball.getAnotherPlayerIndex();
            currentBowlerPosition=ball.getBowlingPlayerIndex();

            battingTeam.getPlayer(battingBatsmanPosition).deletePlayedLastBall(ball.getRun());
            bowlingTeam.getPlayer(currentBowlerPosition).deleteLastBowlingBall();
            if(overs.getLast().isEmpty())
                deleteOver();
            if(ball.isOut()){
                battingTeam.getPlayer(lastWicketBatsmanIndex).setOutType(null);
                battingTeam.getPlayer(nextBatsmanPosition).setBatted(false);
                totalWicket--;
                currentPartnerShip=partnership.getLast();
                partnership.removeLast();
                outtedBatsmanIndex.removeLast();
                if(outtedBatsmanIndex.size()!=0)
                    lastWicketBatsmanIndex=outtedBatsmanIndex.getLast();
                else lastWicketBatsmanIndex=-1;
            }
        }
    }

    public double getPlayedOver(){
        return Over.getOverNumber(overs);
    }

    public double currentRunRate(){
        System.out.println(getPlayedOver());
        if(getPlayedOver()!=0)
            return (.6*totalRun)/getPlayedOver();
        return 0;
    }

    public String battingTeamText(){
        StringBuilder sb=new StringBuilder();
        Formatter formatter=new Formatter(sb, Locale.ENGLISH);
        String string=""+formatter.format("%-16s:%4d/%2d (Over- %3.1f)",battingTeam.getName(),totalRun,totalWicket,((float)getPlayedOver()));
        return string;
        //1stTeam:            1000/10   (Over-90)
    }

    public String bowlingTeamText(){
        StringBuilder sb=new StringBuilder();
        Formatter formatter=new Formatter(sb, Locale.ENGLISH);
        String string=""+formatter.format("%-16s:",bowlingTeam.getName());
        return string;
        //2ndTeam:            1000/10   (Over-90)
    }

    public String getCurrentRunRateText(){
        return ""+new Formatter(new StringBuilder(),Locale.ENGLISH).format("Current Run Rate: %.2f",(float)currentRunRate());
    }

    public String getPartnerShipText(){
        return "Partnership: "+currentPartnerShip;
    }

    public String getLastWicketText(){
        if(lastWicketBatsmanIndex!=-1)
            return "Last Wicket: "+battingTeam.getPlayer(lastWicketBatsmanIndex).getName()+"-"
                +battingTeam.getPlayer(lastWicketBatsmanIndex).totalRun()+"("
                +battingTeam.getPlayer(lastWicketBatsmanIndex).totalBallPlayed()+")";
        return "";
    }

    public Player getBattingBatsman(){
        return battingTeam.getPlayer(battingBatsmanPosition);
    }

    public Player getAnotherBatsman(){
        return battingTeam.getPlayer(anotherBatsmanPosition);
    }

    public Player getCurrentBowler(){
        return bowlingTeam.getPlayer(currentBowlerPosition);
    }

    public Player getNextBatsman(){
        return battingTeam.getPlayer(nextBatsmanPosition);
    }

    public boolean isOverFull(){
        if(overs.size()==0)
            return false;
        return overs.getLast().isFull();
    }

    public boolean isInningsComplete(){
        if(isFinishOver() || totalWicket==Game.getInstance().getNoOfPlayerInOneTeam()-1)
            return true;
        return false;
    }

    /////////////////////getter and setter//////////////////////////////////////////////


    public LinkedList<Over> getOvers() {
        return overs;
    }

    public int getTotalWicket() {
        return totalWicket;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public int getBattingBatsmanPosition() {
        return battingBatsmanPosition;
    }

    public void setBattingBatsmanPosition(int battingBatsmanPosition) {
        this.battingBatsmanPosition = battingBatsmanPosition;
    }

    public int getAnotherBatsmanPosition() {
        return anotherBatsmanPosition;
    }

    public void setAnotherBatsmanPosition(int anotherBatsmanPosition) {
        this.anotherBatsmanPosition = anotherBatsmanPosition;
    }

    public int getCurrentBowlerPosition() {
        return currentBowlerPosition;
    }

    public void setCurrentBowlerPosition(int currentBowlerPosition) {
        this.currentBowlerPosition = currentBowlerPosition;
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

    public int getTotalRun() {
        return totalRun;
    }

    public int getOutBatsmanPosition() {
        return outBatsmanPosition;
    }

    public void setOutBatsmanPosition(int outBatsmanPosition) {
        this.outBatsmanPosition = outBatsmanPosition;
    }

    public int getNextBattingBatsmanPosition() {
        return nextBattingBatsmanPosition;
    }

    public void setNextBattingBatsmanPosition(int nextBattingBatsmanPosition) {
        this.nextBattingBatsmanPosition = nextBattingBatsmanPosition;
    }

    public int getCurrentPartnerShip() {
        return currentPartnerShip;
    }

    public int getLastWicketBatsmanIndex() {
        return lastWicketBatsmanIndex;
    }
}
