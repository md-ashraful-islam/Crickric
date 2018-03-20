package com.example.user.crickric;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.crickric.playerAndGame.Game;
import com.example.user.crickric.playerAndGame.Team;

import java.util.ArrayList;
import java.util.Collections;

public class ChoosePlayerActivity extends AppCompatActivity {
    TextView headingText;
    ListView playersList;
    TextView nowSelected=null;
    TextView previousSelected=null;
    int selectedPlayerPosition=-1;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);
        setHeaderAndPlayerList();
    }

    public void setHeaderAndPlayerList(){
        headingText=findViewById(R.id.headingText);
        playersList=findViewById(R.id.playerList);
        headingText.setText(getIntent().getExtras().getString("key"));
        team = Game.getInstance().isFirstTeamBatting() ? Game.getInstance().getFirstTeam() : Game.getInstance().getSecondTeam();

        ArrayList<String> playersName=new ArrayList<>();
        for(int i=0;i<Game.getInstance().getNoOfPlayerInOneTeam();i++){
            if (!team.getPlayer(i).isBatted())
                playersName.add(team.getPlayer(i).getName());
        }
        String str[]=new String[playersName.size()];
        for(int i=0;i<str.length;i++){
            str[i]=playersName.get(i);
        }
        ArrayAdapter<String> playerNameAdapter=new ArrayAdapter<>(this,R.layout.playername,str);
        playersList.setAdapter(playerNameAdapter);
        setOnClickLeasener();
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
            switch (headingText.getText().toString()){
                case "    Choose a opening Batsman":
                    team.setOpeningBatsmanPosition(selectedPlayerPosition);
                    team.getPlayer(selectedPlayerPosition).setBatted(true);
                    Intent intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                    intent.putExtra("key","Choose another opening Batsman");
                    startActivity(intent);
                    break;
                case "Choose another opening Batsman":
                    team.setAnotherOpeningBatsmanPosition(selectedPlayerPosition);
                    team.getPlayer(selectedPlayerPosition).setBatted(true);
                    break;
            }
        }
        //onBackPressed();
    }
}
