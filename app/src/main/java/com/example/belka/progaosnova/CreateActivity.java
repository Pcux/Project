package com.example.belka.progaosnova;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button buttonCreateAcc = (Button)findViewById(R.id.buttonCreatAcc);
        buttonCreateAcc.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Настройки");
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
                    Intent bCAintent = new Intent(this, RoomActivity.class);
                    startActivity(bCAintent);
                    break;
                }
                else {
                    textView2.setText("passwords do not match");
                    editText7.setText("");
                    editText8.setText("");
                }
                break;

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
