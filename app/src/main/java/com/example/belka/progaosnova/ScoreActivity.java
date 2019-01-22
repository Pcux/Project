package com.example.belka.progaosnova;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends ConstraintLayout {
    Context ctxt;
    public ScoreActivity(Context context) {
        super(context);
        ctxt=context;
    }

    public ScoreActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctxt=context;
    }

    public ScoreActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctxt=context;
    }
    List<TextView> tv = new ArrayList<>(0);
    List<Integer> score = new ArrayList<>(0);
    List<String> nicks = new ArrayList<>(0);
    void Sort(){
        int t=0;
        String s="";
        for (int i= 0; i<nplayers; i++)
            for (int j=i; j<nplayers;j++)
            {
                if(score.get(j).compareTo(score.get(i))==1){
                    t=score.get(i);
                    score.set(i,score.get(j));
                    score.set(j,t);
                    s=nicks.get(i);
                    nicks.set(i,nicks.get(j));
                    nicks.set(j,s);
                }
            }
    }
    public void UpdateText(){
        Sort();
        Integer c = 0;
        for(int i = 0; i<nplayers;i++){
            tv.get(i).setText((new Integer(c+1)).toString()+". "+nicks.get(c)+" "+score.get(c).toString());
            c++;
        }
    }
    Integer nplayers;
    AppCompatActivity acc;
    public void Init(AppCompatActivity acc, Integer nplayers) {
        //ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams;
        this.acc = acc;
        this.nplayers=nplayers;
        for (int i = 0; i < nplayers; i++){
            score.add(0);
            nicks.add("");
        tv.add(new TextView(acc.getApplicationContext()));
        this.addView(tv.get(i));
         //tv.get(i)
        }
    }
    public void SetScores(List<Integer> score){
        int c=0;
        for(Integer i:score){
        this.score.set(c++,i);
        }
    }
    public void SetNicks(List<String> nick){
        int c=0;
        for(String i:nick){
            this.nicks.set(c++,i);
        }
    }

}
