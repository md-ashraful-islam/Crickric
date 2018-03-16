package com.example.user.crickric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class InputGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_game);
    }
    public void nextButtonClickOnAction(View view) {
        AutoCompleteTextView gameName=(AutoCompleteTextView) findViewById(R.id.gameName);
        EditText totalOverText = (EditText) findViewById(R.id.totalOver);
        EditText playerNumberText = (EditText) findViewById(R.id.playerNumber);
        TextView gameNameErrorText =(TextView) findViewById(R.id.gameNameErrorText);
        TextView playerNumberErrorText =(TextView) findViewById(R.id.playerNumberErrorText);
        TextView totalOverErrorText =(TextView) findViewById(R.id.totalOverErrorText);
        boolean allCorrect=true;
        System.out.println("Name: "+gameName.getText());
        if(gameName.getText().equals("")){
            gameNameErrorText.setVisibility(View.VISIBLE);
            allCorrect=false;
            System.out.println("Error in name");
        }
        else {
            gameNameErrorText.setVisibility(View.INVISIBLE);
        }

        try{
            int playerNumber=Integer.parseInt(playerNumberText.getText().toString());
            if(playerNumber<2 || playerNumber>12){
                allCorrect=false;
                playerNumberErrorText.setVisibility(View.VISIBLE);
                System.out.println("Error in player");
            }else {
                playerNumberErrorText.setVisibility(View.INVISIBLE);
            }
        }catch(NumberFormatException e){
            allCorrect=false;
            playerNumberErrorText.setVisibility(View.VISIBLE);
            System.out.println("Error in player");

        }
        try{
            int totalOver=Integer.parseInt(totalOverText.getText().toString());
            if(totalOver<1 || totalOver>90){
                allCorrect=false;
                totalOverErrorText.setVisibility(View.VISIBLE);
                System.out.println("Error in over");
            }else {
                totalOverErrorText.setVisibility(View.INVISIBLE);
            }
        }catch(NumberFormatException e){
            allCorrect=false;
            totalOverErrorText.setVisibility(View.VISIBLE);
            System.out.println("Error in over");
        }

        if(allCorrect){
            System.out.println("All ok");
        }

    }
}
