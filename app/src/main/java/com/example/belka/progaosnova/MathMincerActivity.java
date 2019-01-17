package com.example.belka.progaosnova;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;

public class MathMincerActivity extends AppCompatActivity implements View.OnClickListener{
    int n = 4;
    Button[][] button= new Button[n][n];
    Random r;
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r= new Random();
        //LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = vi.inflate(R.layout.activity_math_mincer, null);
        GridLayout gl = new GridLayout(this);
        //GridLayout gl = findViewById(R.id.favorites_grid);

        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.GridStyle);


        for (Integer i=0; i<n; i++){
            for (Integer j=0; j<n;j++){
                Button btn = new Button(newContext);
                btn.setId(i*10+j);
                btn.setOnClickListener(this);
                button[i][j]=btn;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER);
                param.rightMargin = 5*i;
                param.topMargin = 5*j;
                param.columnSpec = GridLayout.spec(i);
                param.rowSpec = GridLayout.spec(j);
                btn.setLayoutParams (param);
                gl.addView(btn,c++);
            }
        }
        setContentView(R.layout.activity_math_mincer);
//        ConstraintLayout cl = new ConstraintLayout(this);
        ConstraintLayout cl = findViewById(R.id.main);
        cl.addView(gl);
//        setContentView(cl);
        for (Integer i = 0;i<c;i++)
        {
            button[r.nextInt(n)][r.nextInt(n)].setText(i.toString());
        }
    }
    @Override
    public void onClick(View view) {
        button[(view.getId()/10)][(view.getId()%10)].setText("");
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
