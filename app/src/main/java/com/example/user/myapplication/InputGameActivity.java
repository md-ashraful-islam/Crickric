package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crickric.game.Game;

public class InputGameActivity extends AppCompatActivity {
    String gameName=null;
    int totalOver=0;
    int playerNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_game);
    }

    public boolean isGameNameCorrect(){
        AutoCompleteTextView gameName=(AutoCompleteTextView) findViewById(R.id.gameName);
        this.gameName=gameName.getText().toString();
        TextView gameNameErrorText =(TextView) findViewById(R.id.gameNameErrorText);
        if(this.gameName.length()==0){
            gameNameErrorText.setVisibility(View.VISIBLE);
            System.out.println("Error in name");
            return false;
        }
        gameNameErrorText.setVisibility(View.INVISIBLE);
        return true;
    }

    public boolean isPlayerNumberCorrect(){
        EditText playerNumberText = (EditText) findViewById(R.id.playerNumber);
        TextView playerNumberErrorText =(TextView) findViewById(R.id.playerNumberErrorText);

        try{
            playerNumber=Integer.parseInt(playerNumberText.getText().toString());
            if(playerNumber<2 || playerNumber>12){
                playerNumberErrorText.setVisibility(View.VISIBLE);
                System.out.println("Error in player");
                return false;
            }
            playerNumberErrorText.setVisibility(View.INVISIBLE);
            return true;

        }catch(NumberFormatException e){
            playerNumberErrorText.setVisibility(View.VISIBLE);
            System.out.println("Error in player");
            return false;
        }
    }

    public boolean isTotalOverCorrect(){
        EditText totalOverText = (EditText) findViewById(R.id.totalOver);
        TextView totalOverErrorText =(TextView) findViewById(R.id.totalOverErrorText);

        try{
            totalOver=Integer.parseInt(totalOverText.getText().toString());
            if(totalOver<1 || totalOver>90){
                totalOverErrorText.setVisibility(View.VISIBLE);
                System.out.println("Error in over");
                return false;
            }
            totalOverErrorText.setVisibility(View.INVISIBLE);
            return true;
        }catch(NumberFormatException e){
            totalOverErrorText.setVisibility(View.VISIBLE);
            System.out.println("Error in over");
            return false;
        }
    }

    public boolean isAllCorrect(){
        if(isGameNameCorrect()){
            if(isPlayerNumberCorrect()){
                if(isTotalOverCorrect())
                    return true;
            }
            else {
                isTotalOverCorrect();
            }
        }
        else {
            isPlayerNumberCorrect();
            isTotalOverCorrect();
        }
        return false;
    }

    public void nextButtonClickOnAction(View view) {
        if(isAllCorrect()){
            Game.getInstance().initialize();
            System.out.println("All ok");
            Game.getInstance().setName(gameName);
            Game.getInstance().setNoOfPlayerInOneTeam(playerNumber);
            Game.getInstance().setTotalNoOfOver(totalOver);
            Intent intent=new Intent(getApplicationContext(),Input1stTeamInfoActivity.class);
            startActivity(intent);
            return;
        }
    }
}
