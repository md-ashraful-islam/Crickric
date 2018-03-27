package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crickric.game.Game;
import com.example.user.crickric.game.Team;

public class Input1stTeamInfoActivity extends AppCompatActivity {
    TableRow tableRow[]=new TableRow[11];
    EditText editText[]=new EditText[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input1st_team_info);
        setTableRow();
        setEditText();
    }
    
    public void setTableRow(){
        tableRow[0]=(TableRow) findViewById(R.id.tableRow1);
        tableRow[1]=(TableRow) findViewById(R.id.tableRow2);
        tableRow[2]=(TableRow) findViewById(R.id.tableRow3);
        tableRow[3]=(TableRow) findViewById(R.id.tableRow4);
        tableRow[4]=(TableRow) findViewById(R.id.tableRow5);
        tableRow[5]=(TableRow) findViewById(R.id.tableRow6);
        tableRow[6]=(TableRow) findViewById(R.id.tableRow7);
        tableRow[7]=(TableRow) findViewById(R.id.tableRow8);
        tableRow[8]=(TableRow) findViewById(R.id.tableRow9);
        tableRow[9]=(TableRow) findViewById(R.id.tableRow10);
        tableRow[10]=(TableRow) findViewById(R.id.tableRow11);
        for(int i = Game.getInstance().getNoOfPlayerInOneTeam();i<11;i++){
            tableRow[i].setVisibility(View.INVISIBLE);
        }
    }
    
    public void setEditText(){
        editText[0]=(EditText) findViewById(R.id.firstTeamName);
        editText[1]=(EditText) findViewById(R.id.batsman1NameText);
        editText[2]=(EditText) findViewById(R.id.player2);
        editText[3]=(EditText) findViewById(R.id.player3);
        editText[4]=(EditText) findViewById(R.id.player4);
        editText[5]=(EditText) findViewById(R.id.player5);
        editText[6]=(EditText) findViewById(R.id.player6);
        editText[7]=(EditText) findViewById(R.id.player7);
        editText[8]=(EditText) findViewById(R.id.player8);
        editText[9]=(EditText) findViewById(R.id.player9);
        editText[10]=(EditText) findViewById(R.id.player10);
        editText[11]=(EditText) findViewById(R.id.player11);
    }

    public void setFirstTeam(){
        Game.getInstance().setFirstTeam(new Team(editText[0].getText().toString(),Game.getInstance().getNoOfPlayerInOneTeam()));
        for(int i=0;i<Game.getInstance().getNoOfPlayerInOneTeam();i++){
            Game.getInstance().getFirstTeam().addPlayer(editText[i+1].getText().toString());

        }
    }

    public boolean isAllInputCorrect(){
        for (int i=0;i<12;i++){
            if(editText[i].getText().toString().length()==0){
                return false;
            }
        }
        return true;
    }

    public void nextButtonClickOnAction(View view) {
        if(isAllInputCorrect()){
            System.out.println(editText[0].getText().toString());
            setFirstTeam();
            //System.out.println(Game.getInstance().getFirstTeam());
            //System.out.println(Game.getInstance().getSecondTeam());
            Intent intent=new Intent(getApplicationContext(),Input2ndTeamInfoActivity.class);
            startActivity(intent);
        }
        else {
            TextView t=findViewById(R.id.warningText);
            t.setVisibility(View.VISIBLE);
        }
    }
}
