package com.example.belka.progaosnova;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        EnterPlayerNameDialog.NameResultListener {

    public static final String KEY_PLAYER_NAME = "keyPlayerName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
        Button buttonQuit = (Button)findViewById(R.id.buttonQuit);
        buttonQuit.setOnClickListener(this);
        Button buttonMultiplayer = (Button)findViewById(R.id.buttonMultiplayer);
        buttonMultiplayer.setOnClickListener(this);
//        Button buttonSettings = (Button)findViewById(R.id.buttonSettings);
//        buttonSettings.setOnClickListener(this);
//        Button buttonStatistics = (Button)findViewById(R.id.buttonStatistics);
//        buttonStatistics.setOnClickListener(this);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                Intent bPIntent = new Intent(this, SingleplayerActivity.class);
                startActivity(bPIntent);
                break;
            case R.id.buttonQuit:
                //Intent bQintent = new Intent(this, MainActivity.class);
                finish();
                break;
            case R.id.buttonMultiplayer:
                EnterPlayerNameDialog dialog = new EnterPlayerNameDialog(this);
                dialog.setNameResultListener(this);
                dialog.show();
                break;
//            case R.id.buttonSettings:
//                Intent bSeIntent = new Intent(this, SettingsActivity.class);
//                startActivity(bSeIntent);
//                break;
//            case R.id.buttonStatistics:
//                Intent bStIntent = new Intent(this, StatisticsActivity.class);
//                startActivity(bStIntent);
//                break;
        }
    }

    @Override
    public void deliverPlayerName(String playerName) {
        if (!playerName.isEmpty()) {
            Intent bMuIntent = new Intent(this, MathMincerActivity.class);
            bMuIntent.putExtra(KEY_PLAYER_NAME, playerName);
            startActivity(bMuIntent);
        } else {
            // TODO послать игрока куда подальше с пустым именем
        }
    }
}