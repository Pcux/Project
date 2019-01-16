package com.example.belka.progaosnova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    //ghrgbkjwrhbglkhsr
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Button buttonCreateAcc = (Button)findViewById(R.id.buttonCreatAcc);
        buttonCreateAcc.setOnClickListener(this);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    public void onClick(View v) {
        EditText editText6 = (EditText)findViewById(R.id.editText6);
        EditText editText7 = (EditText)findViewById(R.id.editText7);
        EditText editText8 = (EditText)findViewById(R.id.editText8);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        switch (v.getId()) {
            case R.id.buttonCreatAcc:
                String Name,Pass,Doppass;
                Name = editText6.getText().toString();
                Pass = editText7.getText().toString();
                Doppass = editText8.getText().toString();
                if (Pass.equals(Doppass)) {
                    finish();
                }
                else {
                    textView2.setText("passwords do not match");
                    editText7.setText("");
                    editText8.setText("");
                }
                break;
            case R.id.button:
                finish();
                break;
        }
    }
}
