package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.crickric.ball.Ball;
import com.example.user.crickric.game.FirstInnings;
import com.example.user.crickric.game.Game;
import com.example.user.crickric.game.Team;
import com.example.user.crickric.over.OutType;
import com.example.user.crickric.player.PlayerType;

import java.util.ArrayList;

public class ChoosePlayerActivity extends AppCompatActivity {
    TextView headingText;
    ListView playersList;
    TextView nowSelected=null;
    TextView previousSelected=null;
    int selectedPlayerPosition=-1;
    Team team;
    String text;
    FirstInnings innings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);
        text=getIntent().getExtras().getString("key");
        innings=Game.getInstance().getInnings();
        setHeaderAndPlayerList();
        setOnClickLeasener();
    }

    public void setHeaderAndPlayerList(){
        headingText=findViewById(R.id.headingText);
        playersList=findViewById(R.id.playerList);
        headingText.setText(text);
        String str[]=null;
        ArrayList<String> playersName=new ArrayList<>();
        switch (text){
            case "        Choose a bowler       ":
                team=innings.getBowlingTeam();
                if(getIntent().getExtras().get("i")!=null && getIntent().getExtras().get("i").equals("initialize"))
                    team.initializePlayer();
                for(int i=0;i<Game.getInstance().getNoOfPlayerInOneTeam();i++){
                    //i!=innings.getCurrentBowlerPosition() &&
                    if (team.getPlayer(i).getPlayerType()!=PlayerType.WICKETKIPER)
                        playersName.add(team.getPlayer(i).getName());
                }
                str=new String[playersName.size()];
                for(int i=0;i<str.length;i++){
                        str[i]=playersName.get(i);
                }
                break;
            case " Choose first batting Batsman ":
                team=innings.getBattingTeam();
                team.initializePlayer();
            case "      Choose next Batsman     ":
            case "Choose another opening Batsman":
                team=innings.getBattingTeam();
                for(int i=0;i<Game.getInstance().getNoOfPlayerInOneTeam();i++){
                    if (!team.getPlayer(i).isBatted())
                        playersName.add(team.getPlayer(i).getName());
                }
                str=new String[playersName.size()];
                for(int i=0;i<str.length;i++){
                    str[i]=playersName.get(i);
                }
                break;
            case "          Who will bat?       ":
                team=innings.getBattingTeam();
                str=new String[2];
                str[0]=innings.getNextBatsman().getName();
                str[1] = innings.getBattingBatsmanPosition() == innings.getOutBatsmanPosition() ? innings.getAnotherBatsman().getName() : innings.getBattingBatsman().getName();
                break;
            case "           Who is out?        ":
                team=innings.getBattingTeam();
                str=new String[2];
                str[0]=innings.getBattingBatsman().getName();
                str[1]=innings.getAnotherBatsman().getName();
                break;
        }
        ArrayAdapter<String> playerNameAdapter=new ArrayAdapter<>(this, R.layout.playername,str);
        playersList.setAdapter(playerNameAdapter);
    }

    public void setOnClickLeasener(){
        playersList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        previousSelected=nowSelected;
                        nowSelected=(TextView) view;
                        nowSelected.setTextColor(Color.RED);
                        if(previousSelected!=null)
                            previousSelected.setTextColor(Color.BLACK);
                    }
                }
        );
    }

    public int searchPlayerIndex(String name){
        for(int i=0;i<Game.getInstance().getNoOfPlayerInOneTeam();i++){
            if(name.equals(team.getPlayer(i).getName())){
                return i;
            }
        }
        return -1;
    }

    public void nextButtonClickOnAction(View view) {
        if(nowSelected!=null){
            System.out.println("OKK");
            selectedPlayerPosition=searchPlayerIndex(nowSelected.getText().toString());
            System.out.println(nowSelected.getText());
            System.out.println(selectedPlayerPosition);
            Intent intent=null;
            switch (text){
                case " Choose first batting Batsman ":
                    innings.setBattingBatsmanPosition(selectedPlayerPosition);
                    innings.getBattingTeam().getPlayer(selectedPlayerPosition).setBatted(true);
                    intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                    intent.putExtra("key","Choose another opening Batsman");

                    break;
                case "Choose another opening Batsman":
                    innings.setAnotherBatsmanPosition(selectedPlayerPosition);
                    innings.getBattingTeam().getPlayer(selectedPlayerPosition).setBatted(true);
                    intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                    intent.putExtra("key","        Choose a bowler       ");
                    intent.putExtra("i","initialize");

                    break;
                case "        Choose a bowler       ":
                    if(innings.isOverFull())
                        innings.swapPlayerPosition();
                    innings.setCurrentBowlerPosition(selectedPlayerPosition);
                    intent=new Intent(getApplicationContext(),ShowingRunActivity.class);
                    intent.putExtra("key","Operate");
                    break;
                case "      Choose next Batsman     ":
                    innings.setNextBatsmanPosition(selectedPlayerPosition);
                    innings.getBattingTeam().getPlayer(selectedPlayerPosition).setBatted(true);
                    Ball ball=Game.getInstance().getLastBall();
                    if(ball.getOutType()!= OutType.BOLTOUT && ball.getOutType()!=OutType.LBWOUT){
                        intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                        intent.putExtra("key","          Who will bat?       ");
                    }
                    else {
                        innings.setNextBattingBatsmanPosition(selectedPlayerPosition);
                        innings.setPlayerPosition();
                        if(innings.isOverFull() && !innings.isInningsComplete()){
                            intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                            intent.putExtra("key","        Choose a bowler       ");
                        }
                        else {
                            intent=new Intent(getApplicationContext(),ShowingRunActivity.class);
                            intent.putExtra("key","Operate");
                        }
                    }
                    break;
                case "           Who is out?        ":
                    innings.setOutBatsmanPosition(selectedPlayerPosition);
                    innings.getBattingTeam().getPlayer(selectedPlayerPosition).setOutType(Game.getInstance().getLastBall().getOutType());
                    if(innings.isInningsComplete()){
                        intent=new Intent(getApplicationContext(),ShowingRunActivity.class);
                        intent.putExtra("key","Operate");
                    }
                    else{
                        intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                        intent.putExtra("key","      Choose next Batsman     ");
                    }
                    break;
                case "          Who will bat?       ":
                    innings.setNextBattingBatsmanPosition(selectedPlayerPosition);
                    innings.setPlayerPosition();
                    if(innings.isOverFull() && !innings.isInningsComplete()){
                        intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                        intent.putExtra("key","        Choose a bowler       ");
                    }
                    else {
                        intent=new Intent(getApplicationContext(),ShowingRunActivity.class);
                        intent.putExtra("key","Operate");
                    }
                    break;
            }
            startActivity(intent);
        }
        //onBackPressed();
    }


    @Override
    public void onBackPressed(){
        switch (text){
            case "Choose another opening Batsman":
            case "        Choose a bowler       ":
                Intent intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                intent.putExtra("key"," Choose first batting Batsman ");
                startActivity(intent);
                break;
        }
        super.onBackPressed();
    }
}
