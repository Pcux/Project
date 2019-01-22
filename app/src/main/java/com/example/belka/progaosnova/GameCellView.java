package com.example.belka.progaosnova;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AnimationSet;

public class GameCellView extends AppCompatTextView {

    boolean ifSelected = false;

    public GameCellView(Context context, MyAnimationListener al) {
        super(context);
    }

    public GameCellView(Context context, AttributeSet attrs, MyAnimationListener al) {
        super(context, attrs);
    }

    public GameCellView(Context context, AttributeSet attrs, int defStyleAttr, MyAnimationListener al) {
        super(context, attrs, defStyleAttr);
    }
    public GameCellView(Context context) {
        super(context);
    }

    public GameCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(MyAnimationListener al) {
        this.al=new MyAnimationListener();
        this.al.Init(this,0);
        setText("");
        setGravity(Gravity.CENTER);
        colorBackground();
    }
    MyAnimationListener al;
    public void toggle(AnimationSet as,Integer state) {
        ifSelected = !ifSelected;
        al.Init(this,state);
        as.setAnimationListener(al);
        this.startAnimation(as);

    }

     void colorBackground() {
        if (ifSelected) setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        else setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
    }
}
