package com.example.user.crickric;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.example.user.crickric.playerAndGame.Game;

/**
 * Created by User on 18-Mar-18.
 */

public class InputWhoBatFirst extends DialogFragment {
    private AlertDialog alert;

    public void show(){
        alert.show();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Who will bat first?");
        CharSequence[] items=new CharSequence[2];
        items[0]= Game.getInstance().getFirstTeam().getName();
        items[1]=Game.getInstance().getSecondTeam().getName();
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    Game.getInstance().setFirstTeamBatfirst(true);
                }else if(which==1) {
                    Game.getInstance().setFirstTeamBatfirst(false);
                }
            }
        });
        alert = alertDialog.create();
        return (Dialog) alert;
    }
}
