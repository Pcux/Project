package com.example.belka.progaosnova;

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
        int[][] coord;
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
        //pohod(2,2,2);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Gbutton1:
                break;
            case R.id.Gbutton2:
                break;
            case R.id.Gbutton3:
                break;
            case R.id.Gbutton4:
                break;
            case R.id.Gbutton5:
                break;
            case R.id.Gbutton6:
                break;
            case R.id.Gbutton7:
                break;
            case R.id.Gbutton8:
                break;
            case R.id.Gbutton9:
                break;
            case R.id.Gbutton10:
                break;
            case R.id.Gbutton11:
                break;
            case R.id.Gbutton12:
                break;
            case R.id.Gbutton13:
                break;
            case R.id.Gbutton14:
                break;
            case R.id.Gbutton15:
                break;
            case R.id.Gbutton16:
                break;
            case R.id.Gbutton17:
                break;
            case R.id.Gbutton18:
                break;
            case R.id.Gbutton19:
                break;
            case R.id.Gbutton20:
                break;
            case R.id.Gbutton21:
                break;
            case R.id.Gbutton22:
                break;
            case R.id.Gbutton23:
                break;
            case R.id.Gbutton24:
                break;
            case R.id.Gbutton25:
                break;

        }

    }


    public void pohod(int y,int x, int l) {// l - число ходов
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                int a=i-x,b=j-y;
                if (a<0) a*=-1;
                if (b<0) b*=-1;
                if (a+b>l) {
                    button[i][j].setText("");
                }
                else if (i!=x&&j!=y&&a+b<=l) {
                    button[i][j].setText("+");
                }
            }
        }
    }

}
