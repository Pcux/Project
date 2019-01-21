package com.example.belka.progaosnova;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import java.net.URISyntaxException;
import java.util.Random;

public class MathMincerActivity extends AppCompatActivity implements View.OnClickListener, QuestionView.Callback {


    EditText editText;
    int num = 0;
    QuestionView questionView;
    String[][] problems=new String[16][2];
    int[] numProblems = new int[16];
    int[][] statistic = new int[5][3];
    int[][] table = new int[5][5];
    int[] temp = new int[25];
    String namePlayer = "belka";
    Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_mincer);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        num=numProblems[0];
        questionView = findViewById(R.id.questionView);
        questionView.readProblems(problems[num][0]);
        questionView.setCallback(this);
        toSocket();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editText:
                editText.setText("");
                break;
        }
    }
    @Override
    public void checkAnswer(String ans) {
        if (ans.equals(problems[0][1])) {
            questionView.readProblems("+");
        }
        else {
            questionView.readProblems("-");
        }

    }

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

    public void toSocket () {
        try {
        socket = IO.socket("http://95.163.181.238:80");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.emit("updateTable",
                        namePlayer,
                        table[0][0],table[0][1],table[0][2],table[0][3],table[0][4],
                        table[1][0],table[1][1],table[1][2],table[1][3],table[1][4],
                        table[2][0],table[2][1],table[2][2],table[2][3],table[2][4],
                        table[3][0],table[3][1],table[3][2],table[3][3],table[3][4],
                        table[4][0],table[4][1],table[4][2],table[4][3],table[4][4]);
                //socket.disconnect();
                Log.d("socket", Socket.EVENT_CONNECT);
            }

        }).on("event", new Emitter.Listener() {

            @Override
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

                for (int i=0;i<5;i++)
                    for (int j=0;j<5;j++)
                        table[i][j] = (int) args[0];
            }
        });
        socket.connect();
    }
}