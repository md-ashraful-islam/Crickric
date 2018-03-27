package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.crickric.operator.HandleHotspot;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void watchGameButtonClick(View view) {
        System.out.println("Hi");
    }

    public void operateGameButtonClick(View view) {
        HandleHotspot.createHotspot(this);
        Intent intent=new Intent(getApplicationContext(),InputGameActivity.class);
        startActivity(intent);
        System.out.println("Bye");
    }

    @Override
    public void finish() {
        super.finish();
        System.out.println("finish");
        HandleHotspot.closeHotspot(this);
    }
}