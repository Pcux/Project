package com.example.belka.progaosnova;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class MathMincerActivity extends AppCompatActivity implements View.OnClickListener{

    Button[][] button;
    boolean[][] road = new boolean[5][5];
    int[][] coord;
    int l=4;
    int Num = 0;
    int x,y;
    int[][] ball = new int[5][2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_mincer);
        button = new Button[5][5];
        int Storona = 5;
        button[0][0] = (Button)findViewById(R.id.Gbutton1);
        button[0][0].setOnClickListener(this);
        button[0][1] = (Button)findViewById(R.id.Gbutton2);
        button[0][1].setOnClickListener(this);
        button[0][2] = (Button)findViewById(R.id.Gbutton3);
        button[0][2].setOnClickListener(this);
        button[0][3] = (Button)findViewById(R.id.Gbutton4);
        button[0][3].setOnClickListener(this);
        button[0][4] = (Button)findViewById(R.id.Gbutton5);
        button[0][4].setOnClickListener(this);
        button[1][0] = (Button)findViewById(R.id.Gbutton6);
        button[1][0].setOnClickListener(this);
        button[1][1] = (Button)findViewById(R.id.Gbutton7);
        button[1][1].setOnClickListener(this);
        button[1][2] = (Button)findViewById(R.id.Gbutton8);
        button[1][2].setOnClickListener(this);
        button[1][3] = (Button)findViewById(R.id.Gbutton9);
        button[1][3].setOnClickListener(this);
        button[1][4] = (Button)findViewById(R.id.Gbutton10);
        button[1][4].setOnClickListener(this);
        button[2][0] = (Button)findViewById(R.id.Gbutton11);
        button[2][0].setOnClickListener(this);
        button[2][1] = (Button)findViewById(R.id.Gbutton12);
        button[2][1].setOnClickListener(this);
        button[2][2] = (Button)findViewById(R.id.Gbutton13);
        button[2][2].setOnClickListener(this);
        button[2][3] = (Button)findViewById(R.id.Gbutton14);
        button[2][3].setOnClickListener(this);
        button[2][4] = (Button)findViewById(R.id.Gbutton15);
        button[2][4].setOnClickListener(this);
        button[3][0] = (Button)findViewById(R.id.Gbutton16);
        button[3][0].setOnClickListener(this);
        button[3][1] = (Button)findViewById(R.id.Gbutton17);
        button[3][1].setOnClickListener(this);
        button[3][2] = (Button)findViewById(R.id.Gbutton18);
        button[3][2].setOnClickListener(this);
        button[3][3] = (Button)findViewById(R.id.Gbutton19);
        button[3][3].setOnClickListener(this);
        button[3][4] = (Button)findViewById(R.id.Gbutton20);
        button[3][4].setOnClickListener(this);
        button[4][0] = (Button)findViewById(R.id.Gbutton21);
        button[4][0].setOnClickListener(this);
        button[4][1] = (Button)findViewById(R.id.Gbutton22);
        button[4][1].setOnClickListener(this);
        button[4][2] = (Button)findViewById(R.id.Gbutton23);
        button[4][2].setOnClickListener(this);
        button[4][3] = (Button)findViewById(R.id.Gbutton24);
        button[4][3].setOnClickListener(this);
        button[4][4] = (Button)findViewById(R.id.Gbutton25);
        button[4][4].setOnClickListener(this);
        Random random = new Random();
        coord = new int[5][2];
        for (int i=0;i<5;i++) {
            coord[i][0]=i;
            coord[i][1]=random.nextInt(5);
            Integer k = i;
            if (i==0)
                button[i][coord[i][1]].setText("1");
            if (i==1)
                button[i][coord[i][1]].setText("2");
            if (i==2)
                button[i][coord[i][1]].setText("3");
            if (i==3)
                button[i][coord[i][1]].setText("4");
            if (i==4)
                button[i][coord[i][1]].setText("5");
        }
        for (int i=0;i<5;i++) {
            ball[i][0]=0;
            ball[i][1]=0;
            for (int j=0;j<5;j++) {
                road[i][j]=false;
                button[i][j].setBackgroundColor(Color.GRAY);
            }
        }
        pohod(Num,l);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Gbutton1:
                if (road[0][0]) {
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=0,yk=0;
                    perehod(xk,yk);
                }
                break;
            case R.id.Gbutton2:
                if (road[0][1]) {
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=0,yk=1;
                    perehod(xk,yk);
                }
                break;
            case R.id.Gbutton3:
                if (road[0][2]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=0,yk=2;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton4:
                if (road[0][3]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=0,yk=3;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton5:
                if (road[0][4]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=0,yk=4;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton6:
                if (road[1][0]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=1,yk=0;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton7:
                if (road[1][1]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=1,yk=1;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton8:
                if (road[1][2]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=1,yk=2;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton9:
                if (road[1][3]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=1,yk=3;
                    perehod(xk,yk);

                }

                break;
            case R.id.Gbutton10:
                if (road[1][4]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=1,yk=4;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton11:
                if (road[2][0]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=2,yk=0;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton12:
                if (road[2][1]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=2,yk=1;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton13:
                if (road[2][2]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=2,yk=2;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton14:
                if (road[2][3]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=2,yk=3;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton15:
                if (road[2][4]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=2,yk=4;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton16:
                if (road[3][0]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=3,yk=0;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton17:
                if (road[3][1]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=3,yk=1;
                    perehod(xk,yk);
                }
                break;
            case R.id.Gbutton18:
                if (road[3][2]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=3,yk=2;
                    perehod(xk,yk);
                }
                break;
            case R.id.Gbutton19:
                if (road[3][3]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=3,yk=3;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton20:
                if (road[3][4]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=3,yk=4;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton21:
                if (road[4][0]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=4,yk=0;
                    perehod(xk,yk);
                }
                break;
            case R.id.Gbutton22:
                if (road[4][1]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=4,yk=1;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton23:
                if (road[4][2]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=4,yk=2;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton24:
                if (road[4][3]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=4,yk=3;
                    perehod(xk,yk);

                }
                break;
            case R.id.Gbutton25:
                if (road[4][4]){
                    x=coord[Num][0];
                    y=coord[Num][1];
                    int xk=4,yk=4;
                    perehod(xk,yk);

                }
                break;

        }
    }


    public void perehod(int xk,int yk) {
        int a=x-xk,b=y-yk;
        if (a<0) a*=-1;
        if (b<0) b*=-1;
        l=l-a-b;
        Integer numm=Num+1;
        proverka(xk,yk);
        coord[Num][0]=xk;
        coord[Num][1]=yk;
        button[x][y].setText("");
        button[xk][yk].setText(numm.toString());
        if (l>=0) {
            pohod(Num,l);
        }
    }

    public void pohod (int number, int l) {
        int x=coord[number][0];
        int y=coord[number][1];// l - число ходов
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                int a=i-x,b=j-y;
                if (a<0) a*=-1;
                if (b<0) b*=-1;
                if ((i!=x||j!=y)&&a+b<=l) {
                    button[i][j].setBackgroundColor(0xFF0099FF);
                    road[i][j]=true;
                }
                else {
                    button[i][j].setBackgroundColor(0xFFAAAAAA);
                    road[i][j]=false;
                }
            }
        }
    }

    public void proverka(int x,int y) {
        int answer = -1;
        for (int i=0;i<5;i++) {
            if (coord[i][0]==x&&coord[i][1]==y) {
                answer = i;
            }
        }
        if (answer>-1) {
            Random rand = new Random();
            int newX=rand.nextInt(5),newY=rand.nextInt(5);
            int k1=1,k2=1;
            while (k1!=0&&k2!=0) {
                k1=0;
                k2=0;
                for (int i=0;i<5;i++) {
                    if (newX==coord[i][0]) k1++;
                    if (newY==coord[i][1]) k2++;
                }
                if(k1>0) newX=rand.nextInt(5);
                if(k2>0) newY=rand.nextInt(5);
            }
            coord[answer][0] = newX;
            coord[answer][1] = newY;
            Integer otvet = answer+1;
            ball[answer][1]++;
            ball[Num][0]++;
            button[newX][newY].setText(otvet.toString());
        }
    }



}