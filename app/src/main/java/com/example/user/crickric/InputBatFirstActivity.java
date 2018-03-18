package com.example.user.crickric;

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
        team1RadioButton=findViewById(R.id.team1);
        team2RadioButton=findViewById(R.id.team2);
        team1RadioButton.setText(Game.getInstance().getFirstTeam().getName());
        team2RadioButton.setText(Game.getInstance().getSecondTeam().getName());
    }

    public boolean setWhoBatFirst(){
        if(team1RadioButton.callOnClick()){
            Game.getInstance().setFirstTeamBatfirst(true);
        }
        else if(team2RadioButton.callOnClick()) {
            Game.getInstance().setFirstTeamBatfirst(false);
        }
        else
            return false;
        return true;
    }

    public void nextButtonClickOnAction(View view) {
        if(setWhoBatFirst()){
            System.out.println("Success");
        }
    }
}
