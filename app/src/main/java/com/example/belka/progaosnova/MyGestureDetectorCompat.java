package com.example.belka.progaosnova;

import android.content.Context;
import android.view.GestureDetector;

public class MyGestureDetectorCompat extends GestureDetector {
    MyOnGestureListener gl;

    public MyGestureDetectorCompat(Context context, OnGestureListener listener) {
        super(context, listener);
    }
    public void Init (MyOnGestureListener gl){
        this.gl =gl;
    }
}
