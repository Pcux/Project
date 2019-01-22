package com.example.belka.progaosnova;

import android.graphics.Color;
import android.widget.EditText;

public class MyRunnable extends Thread {
    EditText e;
    MyRunnable(EditText e){
        this.e=e;
    }
    @Override
    public void run() {
        e.setTextColor(Color.BLACK);
    }
}
