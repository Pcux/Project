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
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guess_signs:
                Intent guess_signsIntent = new Intent(this, SettingsActivity.class);
                startActivity(guess_signsIntent);
                break;
        }
    }
}
