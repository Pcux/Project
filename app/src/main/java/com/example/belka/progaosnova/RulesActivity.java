package com.example.belka.progaosnova;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Правила");
        textView = findViewById(R.id.textView5);
        textView.setText("Вам представлены три одиночных игры:\n 1) «Расставьте знаки» \n 2) «Выберите правильный ответ»\n 3) «Правда или ложь»\n Рассмотрим каждую игру по подробней.");
        textView = findViewById(R.id.textView6);
        textView.setText("Расставьте знаки");
        textView = findViewById(R.id.textView7);
        textView.setText("Игроку представлено равенство, в котором были стерты знаки, необходимо выбрать знаки так, чтобы тождество выполнялось.");
        textView = findViewById(R.id.textView8);
        textView.setText("Выберите правильный ответ");
        textView = findViewById(R.id.textView9);
        textView.setText("Игроку представлена арифметическая операция и три выбора ответа, необходимо выбрать правильный ответ из предложенных.");
        textView = findViewById(R.id.textView10);
        textView.setText("Правда или ложь");
        textView = findViewById(R.id.textView11);
        textView.setText("Игроку представлено выражение, в котором возможно допущена ошибка. Игрок должен опрделить: выполняется тождество или нет");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
