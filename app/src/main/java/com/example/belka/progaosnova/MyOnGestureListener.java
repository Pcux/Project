package com.example.belka.progaosnova;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyOnGestureListener implements GestureDetector.OnGestureListener {
    Context ctxt;
    Activity acc;
    Integer switchtoid;
    MyOnGestureListener(Integer id,Context ctxt, Activity acc){
        this.ctxt=ctxt;
        this.switchtoid=id;
        this.acc=acc;
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //Intent checkstats = new Intent(ctxt, ScoreActivity.class);
        //acc.startActivity(checkstats);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return true;
    }
}
