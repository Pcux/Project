package com.example.belka.progaosnova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SingleplayerActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer);
        Button guess_signs = (Button)findViewById(R.id.guess_signs);
        guess_signs.setOnClickListener(this);
        Button choose_correct = (Button)findViewById(R.id.choose_correctbutton);
        choose_correct.setOnClickListener(this);
        Button yesorno = (Button)findViewById(R.id.yesorno_button);
        yesorno.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guess_signs:
                Intent guess_signsIntent = new Intent(this, Guess_signsActivity.class);
                startActivity(guess_signsIntent);
                break;
            case R.id.choose_correctbutton:
                Intent choose_correctIntent = new Intent(this, Choose_CorrectActivity.class);
                startActivity(choose_correctIntent);
                break;
            case R.id.yesorno_button:
                Intent yesornoIntent = new Intent(this, YesornoActivity.class);
                startActivity(yesornoIntent);
                break;
        }
    }
}
