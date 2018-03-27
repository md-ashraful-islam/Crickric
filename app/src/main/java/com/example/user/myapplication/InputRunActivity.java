package com.example.user.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.crickric.ball.Ball;
import com.example.user.crickric.ball.BallType;
import com.example.user.crickric.game.FirstInnings;
import com.example.user.crickric.game.Game;
import com.example.user.crickric.over.OutType;

public class InputRunActivity extends AppCompatActivity {
    ListView runList;
    ListView byRunList;
    ListView outList;
    ListView ballTypeList;

    int selectedRunIndex=-1;
    int selectedOutIndex=-1;
    int selectedBallTypeIndex=-1;
    int selectedByRunIndex=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_run);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setList();
        setOnClickListener();
    }

    public void setList(){
        runList=findViewById(R.id.runListView);
        byRunList=findViewById(R.id.byRunListView);
        outList=findViewById(R.id.outListView);
        ballTypeList=findViewById(R.id.ballTypeListView);

        String strings[];
        setRunList:{
            strings=new String[6];
            for(int i=0;i<6;i++){
                if(i==5)
                    strings[i]=(i+1)+"";
                else
                    strings[i]=i+"";
            }
            runList.setAdapter(new ArrayAdapter<>(this, R.layout.playername,strings));

        }

        setByeRunList:{
            strings = new String[4];
            for (int i = 0; i < 4; i++) {
                strings[i] = (i + 1) + "";
            }
            byRunList.setAdapter(new ArrayAdapter<>(this, R.layout.playername, strings));
        }

        int i = 0;
        setOutTypeList:{
            strings = new String[6];
            for (OutType outType : OutType.values()) {
                if (outType.toString().equals("NOTOUT")) {
                    continue;
                }
                strings[i] = outType.toString();
                i++;
            }
            outList.setAdapter(new ArrayAdapter<>(this, R.layout.playername, strings));
        }

        setBallTypeList:{
            strings=new String[4];
            i=0;
            for(BallType ballType:BallType.values()){
                if(ballType.toString().equals("NOTHING")){
                    continue;
                }
                strings[i]=ballType.toString();
                i++;
            }
            ballTypeList.setAdapter(new ArrayAdapter<>(this, R.layout.playername,strings));

        }
    }

    public void setOnClickListener(){
        setOnClickLeasenerForRunListView:{
            runList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(selectedRunIndex==position){
                                ((TextView) view).setTextColor(Color.BLACK);
                                selectedRunIndex=-1;
                            }
                            else {
                                if(selectedRunIndex!=-1){
                                    ((TextView)runList.getChildAt(selectedRunIndex)).setTextColor(Color.BLACK);
                                }
                                selectedRunIndex=position;
                                ((TextView) view).setTextColor(Color.RED);
                            }
                        }
                    }
            );
        }
        setOnClickLeasenerForByRunListView:{
            byRunList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(selectedByRunIndex==position){
                                ((TextView) view).setTextColor(Color.BLACK);
                                selectedByRunIndex=-1;
                            }
                            else {
                                if (selectedByRunIndex != -1) {
                                    ((TextView) byRunList.getChildAt(selectedByRunIndex)).setTextColor(Color.BLACK);
                                }
                                selectedByRunIndex = position;
                                ((TextView) view).setTextColor(Color.RED);
                            }
                        }
                    }
            );
        }
        setOnClickLeasenerForOutTypeListView:{
            outList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(selectedOutIndex==position){
                                ((TextView) view).setTextColor(Color.BLACK);
                                selectedOutIndex=-1;
                            }
                            else {
                                if (selectedOutIndex != -1) {
                                    ((TextView) outList.getChildAt(selectedOutIndex)).setTextColor(Color.BLACK);
                                }
                                selectedOutIndex = position;
                                ((TextView) view).setTextColor(Color.RED);
                            }
                        }
                    }
            );
        }
        setOnClickLeasenerForBallTypeListView:{
            ballTypeList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(selectedBallTypeIndex==position){
                                ((TextView) view).setTextColor(Color.BLACK);
                                selectedBallTypeIndex=-1;
                            }
                            else {
                                if (selectedBallTypeIndex != -1) {
                                    ((TextView) ballTypeList.getChildAt(selectedBallTypeIndex)).setTextColor(Color.BLACK);
                                }
                                selectedBallTypeIndex = position;
                                ((TextView) view).setTextColor(Color.RED);
                            }
                        }
                    }
            );
        }
    }

    public void okButtonClickOnAction(View view) {
        if(selectedRunIndex!=-1 || selectedOutIndex!=-1 || selectedByRunIndex!=-1 || selectedBallTypeIndex!=-1){
            System.out.println("Yes");
            //sentData to all connected spectator
            int run=0;
            if(selectedRunIndex!=-1)
                run=Integer.parseInt(((TextView)runList.getChildAt(selectedRunIndex)).getText().toString());
            BallType ballType=BallType.NOTHING;
            if(selectedBallTypeIndex!=-1)
                ballType=BallType.valueOf(((TextView)ballTypeList.getChildAt(selectedBallTypeIndex)).getText().toString());
            int legByeRun=0;
            if(selectedByRunIndex!=-1)
                legByeRun=Integer.parseInt(((TextView)byRunList.getChildAt(selectedByRunIndex)).getText().toString());

            OutType outType=OutType.NOTOUT;
            if(selectedOutIndex!=-1)
                outType=OutType.valueOf(((TextView)outList.getChildAt(selectedOutIndex)).getText().toString());

            FirstInnings innings=Game.getInstance().getInnings();
            Ball ball=new Ball(run,ballType,outType,legByeRun, innings.getBattingBatsmanPosition(),innings.getAnotherBatsmanPosition(),innings.getCurrentBowlerPosition());
            Game.getInstance().addBall(ball);
            Intent intent=null;
            if(selectedOutIndex==-1){
                if(innings.isOverFull() && !innings.isInningsComplete()){
                    intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                    intent.putExtra("key","        Choose a bowler       ");
                }
                else {
                    intent=new Intent(getApplicationContext(),ShowingRunActivity.class);
                    intent.putExtra("key","Operate");
                }

            }
            else{
                if(outType==OutType.RUNOUT || outType==OutType.HITOUT){
                    intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                    intent.putExtra("key","           Who is out?        ");
                }
                else {
                    Game.getInstance().getInnings().getBattingBatsman().setOutType(outType);
                    Game.getInstance().getInnings().setOutBatsmanPosition(Game.getInstance().getInnings().getBattingBatsmanPosition());
                    intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                    intent.putExtra("key","      Choose next Batsman     ");
                }
            }
            startActivity(intent);
            //onBackPressed();
        }
    }

//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        removeView();
//    }
//
//    public void removeView(){
//        WindowManager windowManager=(WindowManager) getSystemService(WINDOW_SERVICE);
//        if(runList!=null)
//            windowManager.removeView(runList);
////        windowManager.removeView(byRunList);
////        windowManager.removeView(outList);
////        windowManager.removeView(ballTypeList);
//    }
}
