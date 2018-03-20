package com.example.user.crickric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.user.crickric.playerAndGame.Game;

public class InputBatFirstActivity extends AppCompatActivity {
    RadioButton team1RadioButton;
    RadioButton team2RadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bat_first);
        setRadioButtonAndAction();
    }

    public void setRadioButtonAndAction(){
        team1RadioButton=findViewById(R.id.team1);
        team2RadioButton=findViewById(R.id.team2);
        team1RadioButton.setText(Game.getInstance().getFirstTeam().getName());
        team2RadioButton.setText(Game.getInstance().getSecondTeam().getName());
        team1RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(team2RadioButton.isChecked()){
                    team2RadioButton.setChecked(false);
                }
            }
        });
        team2RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(team1RadioButton.isChecked())
                    team1RadioButton.setChecked(false);
            }
        });
    }

    public boolean setWhoBatFirst(){
        if(team1RadioButton.isChecked()){
            Game.getInstance().setFirstTeamBatting(true);
        }
        else if(team2RadioButton.isChecked()) {
            Game.getInstance().setFirstTeamBatting(false);
        }
        else
            return false;
        return true;
    }

    public void nextButtonClickOnAction(View view) {
        if(setWhoBatFirst()){
            System.out.println("Success");
            //System.out.println(Game.getInstance().getFirstTeam());
            //System.out.println(Game.getInstance().getSecondTeam());
            Intent intent=new Intent(getApplicationContext(),ChoosePlayerActivity.class);
            intent.putExtra("key","    Choose a opening Batsman");
            startActivity(intent);
        }
    }
}
