package com.example.belka.progaosnova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Choose_CorrectActivity extends AppCompatActivity implements View.OnClickListener{

    int correctAnswer;
    int bestLevel=0;
    boolean pop=false;
    boolean lol=true;
    boolean bag=true;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;
    TextView textObjectLevel;
    int currentScore = 0;
    int currentLevel = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_correct);

        textObjectPartA = (TextView)findViewById(R.id.textPartA);
        textObjectPartB = (TextView)findViewById(R.id.textPartB);
        textObjectScore = (TextView)findViewById(R.id.textScore);
        textObjectLevel = (TextView)findViewById(R.id.textLevel);
        buttonObjectChoice1 = (Button)findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = (Button)findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = (Button)findViewById(R.id.buttonChoice3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Выбери правильный ответ");
        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);

        setQuestion();

    }

    @Override
    public void onClick(View view) {

        int answerGiven = 0;

        switch (view.getId()) {
            case R.id.buttonChoice1:
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;

            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;

            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;
            /*case R.id.back:
                finish();
                lol=false;
                bag=false;
                break;*/

        }
        if (bag) {
            updateScoreAndLevel(answerGiven);
            setQuestion();
        }
    }

    void setQuestion(){
        TextView textOperator = (TextView)findViewById(R.id.textOperator);
        int numberRange = currentLevel * 3;
        Random randInt = new Random();
        int partA = randInt.nextInt(numberRange);
        partA++;
        int partB = randInt.nextInt(numberRange);
        partB++;

        int sign = randInt.nextInt(4);
        if (sign==0){
            correctAnswer = partA + partB;
            textOperator.setText(""+"+");
        }
        if (sign==1) {
            correctAnswer = partA - partB;
            textOperator.setText(""+"-");
        }
        if (sign==2) {
            correctAnswer = partA * partB;
            textOperator.setText(""+"*");
        }
        if (sign==3) {
            if (partA%partB==0)
                correctAnswer = partA / partB;
            else {
                partA=partB*partA;
                correctAnswer = partA/partB;
            }
            textOperator.setText(""+"/");
        }

        int wrongAnswer1 = correctAnswer-randInt.nextInt(10)-1;
        int wrongAnswer2 = correctAnswer+randInt.nextInt(10)+1;
        textObjectPartA.setText(""+partA);
        textObjectPartB.setText(""+partB);

        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout){
            case 0:
                buttonObjectChoice1.setText(""+correctAnswer);
                buttonObjectChoice2.setText(""+wrongAnswer1);
                buttonObjectChoice3.setText(""+wrongAnswer2);
                break;
            case 1:
                buttonObjectChoice2.setText(""+correctAnswer);
                buttonObjectChoice3.setText(""+wrongAnswer1);
                buttonObjectChoice1.setText(""+wrongAnswer2);
                break;
            case 2:
                buttonObjectChoice3.setText(""+correctAnswer);
                buttonObjectChoice1.setText(""+wrongAnswer1);
                buttonObjectChoice2.setText(""+wrongAnswer2);
                break;
        }
    }

    void updateScoreAndLevel(int answerGiven){

        if(isCorrect(answerGiven)){
//for(int i = 1; i <= currentLevel; i++)
            currentScore = currentScore + currentLevel;
            currentLevel++;
            if(bestLevel<=currentLevel) {
                pop=true;
                bestLevel=currentLevel;
            }
        }
        else{
            currentScore = 0;
            currentLevel = 1;
        }
        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

    boolean isCorrect(int answerGiven){

        boolean correctTrueOrFalse;
        if(answerGiven == correctAnswer){
           correctTrueOrFalse=true;
        }else{
            if(pop && lol)
                Toast.makeText(getApplicationContext(), "Wrong answer \nScore:" + currentScore + "  Level: " + currentLevel + "\nCongratulations! This is the best result of the session", Toast.LENGTH_LONG).show();
            else if (lol)
                Toast.makeText(getApplicationContext(), "Wrong answer \nScore:" + currentScore + "  Level: " + currentLevel + "\nBest level: " + bestLevel, Toast.LENGTH_LONG).show();
            correctTrueOrFalse=false;
            pop=false;
        }

        return correctTrueOrFalse;
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

