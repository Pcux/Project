package com.example.belka.progaosnova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiplayerActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        Button buttonNewAcc = (Button)findViewById(R.id.buttonNewAcc);
        buttonNewAcc.setOnClickListener(this);
        Button buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNewAcc:
                Intent NAintent = new Intent(this,CreateActivity.class);
                startActivity(NAintent);
                break;
            case R.id.buttonNext:
                break;
            case R.id.button:
                finish();
                break;
        }
    }
}