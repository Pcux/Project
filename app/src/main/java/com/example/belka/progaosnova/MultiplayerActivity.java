package com.example.belka.progaosnova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MultiplayerActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editText2;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        Button buttonNewAcc = (Button)findViewById(R.id.buttonNewAcc);
        buttonNewAcc.setOnClickListener(this);
        Button buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText = findViewById(R.id.editText);
        editText.setOnClickListener(this);
        editText2 = findViewById(R.id.editText2);
        editText2.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Сетевая игра");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNewAcc:
                Intent NAintent = new Intent(this,CreateActivity.class);
                startActivity(NAintent);
                break;
            case R.id.buttonNext:
                Intent bNintent = new Intent(this,MathMincerActivity.class);
                startActivity(bNintent);
                break;
            case R.id.editText:
                editText.setText("");
                break;
            case R.id.editText2:
                editText2.setText("");
                break;

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}