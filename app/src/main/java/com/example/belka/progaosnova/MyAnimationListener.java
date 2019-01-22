package com.example.belka.progaosnova;

import android.app.Application;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

public class MyAnimationListener implements Animation.AnimationListener {
    GameCellView parent;
    Integer state;
    void Init(GameCellView parent,Integer state){
        this.parent=parent;this.state=state;
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (state==0){
        parent.setAlpha((float)0.1);
        parent.colorBackground();
        AnimationSet as1= new AnimationSet(true);
            as1.setInterpolator(new AccelerateDecelerateInterpolator());
            AlphaAnimation aa = new AlphaAnimation((float)0.1,(float)1.0);
            aa.setDuration(700);
            aa.setBackgroundColor(parent.getResources().getColor(android.R.color.holo_blue_bright));
            as1.addAnimation(aa);
            parent.toggle(as1,2);}else {
            if (state == 2) {
                parent.setAlpha((float) 1.0);
                this.state = 0;
                parent.ifSelected = !parent.ifSelected;
            } else {
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
