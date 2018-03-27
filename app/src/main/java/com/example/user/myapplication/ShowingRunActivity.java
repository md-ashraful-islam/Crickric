package com.example.user.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.crickric.ball.Ball;
import com.example.user.crickric.game.FirstInnings;
import com.example.user.crickric.game.Game;
import com.example.user.crickric.game.SecondInnings;
import com.example.user.crickric.over.Over;

public class ShowingRunActivity extends AppCompatActivity {
    FirstInnings innings;
    static volatile int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_run);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        innings= Game.getInstance().getInnings();
        checkIsCompleteInnings();
        setInningsText();
        setTargetText();
        setPlayerText();
        setInputButton();
        setRecentRuns();
    }

    public void checkIsCompleteInnings(){
        if(innings.isInningsComplete()){
            System.out.println("Complete");
            if(Game.getInstance().isFirstInnings()){
                System.out.println("show message: 1st innings finish");
                //show message: 1st innings finish
                Game.getInstance().setFirstInnings(false);
                innings=Game.getInstance().getInnings();
                innings.setBattingTeam(Game.getInstance().getFirstInnings().getBowlingTeam());
                innings.setBowlingTeam(Game.getInstance().getFirstInnings().getBattingTeam());
                ((SecondInnings) innings).setTargetRun(Game.getInstance().getFirstInnings().getTotalRun()+1);
                Intent intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                intent.putExtra("key"," Choose first batting Batsman ");
                startActivity(intent);
            }
            else {
                if(((SecondInnings)innings).isBattingTeamWin()){
                    System.out.println("show message: game is finish and batting team win.");
                    //show message: game is finish and batting team win.
                }
                else {
                    //show message: game is finish and bowling team win.

                }
            }
        }
    }

    public void setInningsText(){
        ((TextView)findViewById(R.id.firstTeamStatus)).setText(innings.battingTeamText());
        ((TextView)findViewById(R.id.secondTeamStatus)).setText(innings.bowlingTeamText());
        ((TextView)findViewById(R.id.currentRunRateText)).setText(innings.getCurrentRunRateText());
        ((TextView)findViewById(R.id.partneshipText)).setText(innings.getPartnerShipText());
        ((TextView)findViewById(R.id.lastWicketText)).setText(innings.getLastWicketText());
    }
    public void setTargetText(){
        if(!Game.getInstance().isFirstInnings()){
            SecondInnings secondInnings=(SecondInnings) innings;
            ((TextView)findViewById(R.id.targetText)).setText(secondInnings.getTargetText());
            ((TextView)findViewById(R.id.requredRunRateText)).setText(secondInnings.getRequiredRunRateText());
            ((TextView)findViewById(R.id.currentTargetStatusText)).setText(secondInnings.getTargetStatus());
        }
        else {
            (findViewById(R.id.targetText)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.requredRunRateText)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.currentTargetStatusText)).setVisibility(View.INVISIBLE);
        }
    }

    public void setPlayerText(){
        ((TextView)findViewById(R.id.batsman1StatusText)).setText("*"+innings.getBattingBatsman().getBatsmanTextFormat());
        ((TextView)findViewById(R.id.batsman2StatusText)).setText(" "+innings.getAnotherBatsman().getBatsmanTextFormat());
        ((TextView)findViewById(R.id.bowlerStatusText)).setText(innings.getCurrentBowler().getBowlerTextFormat());
    }

    public void setInputButton(){
        if(!getIntent().getExtras().get("key").equals("Operate")){
            findViewById(R.id.inputRunButton).setVisibility(View.INVISIBLE);
        }
    }

    public void setRecentRuns(){
        LinearLayout runView=findViewById(R.id.runShowingLayout);
        for(Over over:innings.getOvers()){
            for(Ball ball:over.getBalls()){
                TextView textView=new TextView(this);
                textView.setTextSize(36);
                textView.setText(ball.getBallTextFormat());
                runView.addView(textView);
            }
        }
    }

    public void inputRunButtonClickOnAction(View view) {
        i++;
        Intent intent=new Intent(getApplicationContext(),InputRunActivity.class);
        startActivity(intent);
    }

    public void scorecardButtonClickOnAction(View view) {

    }

    public void undoButtonClickOnAction(View view) {
        i--;
        Game.getInstance().deleteLastBall();
        Intent intent;
        if(innings.isOverFull()){
            intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
            intent.putExtra("key","        Choose a bowler       ");
        }
        else {
            intent=new Intent(getApplicationContext(),ShowingRunActivity.class);
            intent.putExtra("key","Operate");
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        i--;
        if(i==0){
            Intent intent=new Intent(getApplicationContext(),InputBatFirstActivity.class);
            startActivity(intent);
        }
        else
            super.onBackPressed();
    }
}