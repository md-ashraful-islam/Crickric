package com.example.user.crickric.game;

import com.example.user.crickric.over.Over;

import java.util.Formatter;
import java.util.Locale;

/**
 * Created by User on 23-Mar-18.
 */

public class SecondInnings extends FirstInnings implements Target  {
    private int targetRun;

    public SecondInnings() {}

    @Override
    public void initialize() {
        super.initialize();
        this.targetRun = 0;
    }

    @Override
    public String bowlingTeamText(){
        StringBuilder sb=new StringBuilder();
        Formatter formatter=new Formatter(sb, Locale.ENGLISH);
        String string=""+formatter.format("%-16s:%4d/%2d (Over- %3.1f)",Game.getInstance().getFirstInnings().getBattingTeam().getName(),Game.getInstance().getFirstInnings().getTotalRun(),Game.getInstance().getFirstInnings().getTotalWicket(),((float)Game.getInstance().getFirstInnings().getPlayedOver()));
        return string;
        //2ndTeam:            1000/10   (Over-90)
    }

    public String getTargetText(){
        return "Target- "+getTargetRun();
    }

    public String getTargetStatus(){
        return "Needs "+requiredRun()+" runs in "+remainingBall()+" balls";
    }

    public String getRequiredRunRateText(){
        return ""+new Formatter(new StringBuilder(),Locale.ENGLISH).format("Required Run Rate: %.2f",(float)requiredRunRate());
    }

    public void setTargetRun(int targetRun) {
        this.targetRun = targetRun;
    }

    @Override
    public int getTargetRun() {
        return targetRun;
    }

    @Override
    public double requiredRunRate() {
        return (requiredRun()*6.0)/remainingBall();
    }

    @Override
    public int remainingBall() {
        return Game.getInstance().getTotalNoOfOver()*6- Over.convertToBall(getPlayedOver());
    }

    @Override
    public int requiredRun() {
        return targetRun-getTotalRun();
    }

    @Override
    public boolean isBattingTeamWin() {
        if(requiredRun()<=0)
            return true;
        return false;
    }

    @Override
    public boolean isInningsComplete(){
        if(super.isInningsComplete() || requiredRun()<=0)
            return true;
        return false;
    }


}
