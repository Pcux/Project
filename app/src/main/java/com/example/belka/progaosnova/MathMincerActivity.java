package com.example.belka.progaosnova;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class MathMincerActivity extends AppCompatActivity implements View.OnClickListener, QuestionView.Callback {


    EditText editText;
    int num = 0;
    QuestionView questionView;
    String[][] problems = new String[16][2];
    int[] numProblems = new int[16];
    int[][] statistic = new int[5][3];
    int[][] table = new int[5][5];
    int[] temp = new int[25];
    String namePlayer = "belka";
    Socket socket;


    GameFieldView grid;

    /*MyGestureDetectorCompat gd;
    MyOnGestureListener gl;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_mincer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try {
            //btn = findViewById(R.id.button);
            grid = findViewById(R.id.grid);
            //score = findViewById(R.id.Score);
            //score.setVisibility(View.INVISIBLE);
            grid.init(this);
            // наслаждаемся API нашей вьюхи
            grid.setSideCount(nplayers);
            grid.setSpaceBetweenCells(8);
            //gl = new MyOnGestureListener(R.id.Score,getApplicationContext(),this);
            //gd = new MyGestureDetectorCompat(getApplicationContext(),gl);
            //gd.Init(gl);
            /*View.OnTouchListener touchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // pass the events to the gesture detector
                    // a return value of true means the detector is handling it
                    // a return value of false means the detector didn't
                    // recognize the event
                    try{
                        return gd.onTouchEvent(event);} catch (Exception e){
                        e.getMessage();
                    }
                    return gd.onTouchEvent(event);

                }};
            grid.setOnTouchListener(touchListener);*/
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Мат. мясорубка");
        } catch (Exception e) {
            System.out.println('e');
        }
        // итого в MainActivity минимум кода, осталась только бизнес логика, то есть кол-во ячеек
        // и кнопка с переключение ячеек, все остальное инкапсулировано в специализированные классы

        problems[0][0] = "Найдите сумму коэффициентов при чётных степенях в многочлене, который получается из выражения  f(x)=(x^3 – x + 1)^100  в результате раскрытия скобок.";
        problems[0][1] = "1";
        problems[1][0] = "Даны многочлены P1, P2, ..., P5, имеющие суммы коэффициентов, равные 1, 2, 3, 4, 5 соответственно. Найдите сумму коэффициентов многочлена  Q=P1P2...P5.";
        problems[1][1] = "120";
        problems[2][0] = "Вычислите коэффициент при x^100 в многочлене  (1 + x + x^2 + ... + x^100)^3  после приведения всех подобных членов.";
        problems[2][1] = "5151";
        problems[3][0] = "Пусть  P(x) = (2x^2–2x+1)^17 * (3x^2–3x+ 1)^17.  Найдите сумму коэффициентов этого многочлена.";
        problems[3][1] = "1";
        problems[4][0] = "Было семь ящиков. В некоторые из них положили еще по семь ящиков (не вложенных друг в друга) и т.д. В итоге стало 10 непустых ящиков. Сколько всего стало ящиков?";
        problems[4][1] = "77";
        problems[5][0] = "Лягушка прыгает по вершинам треугольника ABC, перемещаясь каждый раз в одну из соседних вершин. Сколькими способами она может попасть из A в A за 8 прыжков?";
        problems[5][1] = "86";
        problems[6][0] = "Какое наименьшее число карточек спортлото (6 из 49) надо купить, чтобы наверняка хоть в одной из них был угадан хоть один номер?";
        problems[6][1] = "8";
        problems[7][0] = "30 человек голосуют по пяти предложениям. Сколькими способами могут распределиться голоса, если каждый голосует только за одно предложение?";
        problems[7][1] = "46376";
        problems[8][0] = "Пишется наудачу некоторое двузначное число. Какова вероятность того, что сумма цифр этого числа равна 5? (Пример ответа 1/3).";
        problems[8][1] = "1/18";
        problems[9][0] = "В классе 25 детей. Для дежурства наугад выбирают двоих. Вероятность того, что оба дежурных окажутся мальчиками, равна 3/25. Сколько в классе девочек?";
        problems[9][1] = "16";
        problems[10][0] = "Игрок бросает две кости. Какова вероятность, что сумма выпавших чисел равна 4?";
        problems[10][1] = "1/12";
        problems[11][0] = "Три ковбоя повесили свои шляпы при входе в салун, уходя, они разобрали три шляпы наугад. Найдите вероятность того, что никто из них не взял свою собственную шляпу. (Пример ответа 1/3)";
        problems[11][1] = "1/3";
        problems[12][0] = "Продолжите последовательность чисел: 1, 11, 21, 1112, 3112, 211213, 312213, 212223, 114213... ";
        problems[12][1] = "31121314";
        problems[13][0] = "Найти сумму. Найти сумму 1/2+1/(2*3)+1/(3*4)+ … 1/(2002*2003). (Пример ответа 1/3)";
        problems[13][1] = "2002/2003";
        problems[14][0] = "Найдите количество слов длины 10, состоящих только из букв a и б и не содержащих в записи двух букв б подряд.";
        problems[14][1] = "144";
        problems[15][0] = "Периоды двух последовательностей – 7 и 13. Какова максимальная длина начального куска, который может у них совпадать?";
        problems[15][1] = "18";


        editText = findViewById(R.id.editText);
        editText.setOnClickListener(this);

        randomListProblems();
        num = 0;
        questionView = findViewById(R.id.questionView);
        questionView.nextProblem(num,problems[numProblems[num]][0]);
        questionView.setCallback(this);
        toSocket();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    int nplayers=5;
public  void UpdateTable(){
        for (int i=0; i<nplayers; i++)
            for(int j=0; j<nplayers; j++){
                grid.UpdateCell(i,j, ( (Integer) table[i][j]).toString());
            }
}
   public void toSocket() {
        try {
            socket = IO.socket("http://95.163.181.238:80");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                //socket.emit("message", namePlayer, "0 0 1 0 1 0 1 0 1 1");
                String tableToSocket = encode();
                socket.emit("message",namePlayer, tableToSocket);
                Log.d("socket", Socket.EVENT_CONNECT);
            }
        }).on("event", new Emitter.Listener() {
            public void call(Object... args) {
                Log.d("socket", "event");
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("socket", Socket.EVENT_DISCONNECT);
            }

        }).on("updateTable", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
//                for (int i = 0; i < nplayers; i++)
//                    for (int j = 0; j < nplayers; j++) {
//                        table[i][j] = (int) args[0];
//                    }
            }
        });
        socket.connect();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editText:
                editText.setText("");
                if (bol){
                    editText.setTextColor(Color.BLACK);
                    bol=!bol;
                }
                break;
        }
    }
    int n=0;
    int l=2;
    public void checkAnswer(Integer num, String ans) {
        if(problems[numProblems[num]][1].equals(ans)){
            grid.setpoints(l);
            l=2;
            editText.setText("");
            questionView.nextProblem(num++,problems[numProblems[num]][0]);
        }else {
            l--;
            bol=true;
            editText.setTextColor(Color.RED);
            editText.setText("Неправильный ответ");
            //      Thread t = new Thread(new MyRunnable(editText));
            //    t.run();
            if (l == 0) {questionView.nextProblem(++num, problems[numProblems[num]][0]); l=2; };
        }
    }
boolean bol=false;
    public void randomListProblems() {
        Random rand = new Random();
        for (int i=0;i<16;i++) {
            numProblems[i]=i;
        }
        for (int i=0;i<16;i++) {
            int k=rand.nextInt(16);
            int l;
            l=numProblems[i];
            numProblems[i]=numProblems[k];
            numProblems[k]=l;
        }
    }
public String encode() {
        String result="";
        for (int i=0; i<nplayers;i++)
            for (int j=0; j<nplayers;j++)
            {
                result=(grid.cells.get(i).get(j).getText()=="")?result+"0 ":result+(grid.cells.get(i).get(j).getText()+" ");
            }
            return result;
}
    public void decode(String ss) {
        char[] s=ss.toCharArray();
        int c1=0;
        int c2=0;
        for(int i=0;i<ss.length();i+=2){
            grid.cells.get(c1).get(c2++).setText(s[i]);
            if (c2>nplayers){c2=0; c1++;}
        }
        UpdateTable();
    }

}