package com.example.belka.progaosnova;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionView extends ConstraintLayout {

    TextView textView;
    Callback callback;
    Button buttonOt;
    EditText editText;
    int num;

    public QuestionView(Context context) {
        super(context);
        init();
    }

    public QuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuestionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_question, this);
        textView = getRootView().findViewById(R.id.textView);
        buttonOt = getRootView().findViewById(R.id.buttonOt);
        editText = getRootView().findViewById(R.id.editText);
        buttonOt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.checkAnswer(num,editText.getText().toString());
            }
        });
    }

    public void nextProblem(Integer num, String s){
        textView.setText(s);
        this.num=num;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        public void checkAnswer(Integer num, String ans);

    }

}
