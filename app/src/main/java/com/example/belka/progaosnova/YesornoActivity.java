package com.example.belka.progaosnova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class YesornoActivity extends AppCompatActivity implements View.OnClickListener{

    int correctAnswer;
    Button buttonNo;
    Button buttonYes;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectSign;
    TextView textObjectScore;
    TextView textObjectLevel;
    TextView textObjectAnswer;
    int currentScore = 0;
    int currentLevel = 1;
    int bestLevel = 1;
    boolean pop=false;
    boolean lol=true;
    boolean bag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesorno);
        textObjectPartA = (TextView)findViewById(R.id.textViewA);
        textObjectPartB = (TextView)findViewById(R.id.textViewB);
        textObjectScore = (TextView)findViewById(R.id.textScore);
        textObjectLevel = (TextView)findViewById(R.id.textLevel);
        textObjectSign = (TextView)findViewById(R.id.textViewS);
        textObjectAnswer = (TextView)findViewById(R.id.textViewAns);
        buttonNo = (Button)findViewById(R.id.buttonNo);
        buttonYes = (Button)findViewById(R.id.buttonYes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Правда или ложь?");
        buttonYes.setOnClickListener(this);
        buttonNo.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View view) {

        int answerGiven = 0;
        answerGiven = Integer.parseInt("" + textObjectAnswer.getText());
        boolean of;
        boolean buttof=false;
        if (answerGiven == correctAnswer) of=true;
        else of=false;
        switch (view.getId()) {
            case R.id.buttonYes:
                if (of) {
                    buttof = true;
                }
                else buttof = false;
                break;
            case R.id.buttonNo:
                if (!of) {
                    buttof = true;
                }
                else buttof = false;
                break;
            /*case R.id.back:
                finish();
                lol=false;
                bag=false;
                break;*/
        }
        if (bag) {
            updateScoreAndLevel(buttof);
            setQuestion();
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

    void setQuestion() {
        int numberRange = currentLevel * 3;
        Random randInt = new Random();
        int partA = randInt.nextInt(numberRange);
        partA++;
        int partB = randInt.nextInt(numberRange);
        partB++;

        int sign = randInt.nextInt(4);
        if (sign==0){
            correctAnswer = partA + partB;
            textObjectSign.setText(""+"+");
        }
        if (sign==1) {
            correctAnswer = partA - partB;
            textObjectSign.setText(""+"-");
        }
        if (sign==2) {
            correctAnswer = partA * partB;
            textObjectSign.setText(""+"*");
        }
        if (sign==3){
            if (partA%partB==0)
                correctAnswer = partA / partB;
            else {
                partA=partB*partA;
                correctAnswer = partA/partB;
            }
            textObjectSign.setText(""+"/");
        }

        int wrongAnswer = correctAnswer+randInt.nextInt(10)-5;
        if (wrongAnswer == correctAnswer) wrongAnswer++;
        textObjectPartA.setText(""+partA);
        textObjectPartB.setText(""+partB);

        int pravda = randInt.nextInt(2);
        if (pravda == 0) {
            textObjectAnswer.setText("" + wrongAnswer);
        }
        else {
            textObjectAnswer.setText("" + correctAnswer);
        }
    }
    void updateScoreAndLevel(boolean butt){

        if(butt){
//for(int i = 1; i <= currentLevel; i++)
            currentScore = currentScore + currentLevel;
            currentLevel++;
            if(bestLevel<currentLevel-1) {
                bestLevel=currentLevel-1;
                pop=true;
            }
        }
        else{
            if (pop && lol) {
                Toast.makeText(getApplicationContext(), "Неправильный ответ \nСчет:" + currentScore + "  Уровень:" + currentLevel + "\nПоздравляю! это лучший результат за сессию", Toast.LENGTH_LONG).show();
            }
            else if (lol)
                Toast.makeText(getApplicationContext(), "Неправильный ответ \nСчет:" + currentScore + "  Уровень:" + currentLevel + "\nЛучший уровень: " + bestLevel, Toast.LENGTH_LONG).show();
            pop=false;
            currentScore = 0;
            currentLevel = 1;
        }
        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

}

