package com.example.belka.progaosnova;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AnimationSet;

public class GameCellView extends AppCompatTextView {

    boolean ifSelected = false;


    public GameCellView(Context context) {
        super(context);
    }

    public GameCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

boolean myfishka=false;
    public void init() {
        setText("");
        setGravity(Gravity.CENTER);
        colorBackground();
    }
    public void setMyfishka(boolean myfishka){
        this.myfishka=myfishka;
        colorBackground();
    }
    public void toggle(Integer state) {
        ifSelected = !ifSelected;
        colorBackground();
        // al.Init(this,state);
        //as.setAnimationListener(al);
        //this.startAnimation(as);

    }

     void colorBackground() {
        if(!myfishka){
        if (ifSelected) setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        else setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));}
        else
        {
            setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
    }
}
