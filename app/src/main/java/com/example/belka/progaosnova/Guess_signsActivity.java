package com.example.belka.progaosnova;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.abs;

public class Guess_signsActivity extends AppCompatActivity
        implements View.OnClickListener,RadioGroup.OnCheckedChangeListener
{
    class Background extends Thread{
        boolean result;
        Integer delay;
        private void reset(){
            gstvsl.setText(uch2=='+'?"+":uch2=='-'?"-":uch2=='*'?"*":uch2=='/'?"/":" ? ");
            gstvsr.setText(uch4=='+'?"+":uch4=='-'?"-":uch4=='*'?"*":uch4=='/'?"/":" ? ");
        }
        private void writecorrect(){
           /* gstvdr.setText((ch1=='-')?((i3<0)?'('+i3.toString()+')'+')':i3.toString()+')'):((i3<0)?'('+i3.toString()+')':i3.toString()));
            gstvdl.setText((i<0)?'('+i.toString()+')':i.toString());
            gstvdm.setText(i2.toString());
            gstvsl.setText((ch1=='-')?(new String()+ch1+'('):new String()+ch1);
            gstvsr.setText(new String()+ch2);*/
           gstvdm.setText("***");
        }
        public Background(int delay){
            this.delay=delay;
        }
        public Background(){
            this.delay=0;
        }
        private void check(){
            Integer temp=(uch4=='+')?t2+t3:(uch4=='-')?t2-t3:(uch4=='*')?t2*t3:(uch4=='/')?t2/t3:-300;
            if (temp==-300) {Toast.makeText(getApplicationContext(), temp.toString()+" != "+ans.toString()+" "+uch2+" "+uch4, Toast.LENGTH_LONG).show(); result= false;}
            temp=(uch2=='+')?temp+t1:(uch2=='-')?t1-temp:(uch2=='*')?t1*temp:(uch2=='/')?t1/temp:-300;
            if (temp==-300) {Toast.makeText(getApplicationContext(), temp.toString()+" != "+ans.toString()+" "+uch2+" "+uch4, Toast.LENGTH_LONG).show(); result= false;}
            if (temp.equals(ans)) {Toast.makeText(getApplicationContext(), "You right!", Toast.LENGTH_LONG).show(); result= true;} else
            {Toast.makeText(getApplicationContext(), temp.toString()+" != "+ans.toString()+" "+uch2+" "+uch4, Toast.LENGTH_LONG).show(); result= false;}
        }
        private  void clear(){
            rg2.clearCheck();
            rg4.clearCheck();
            uch2=' ';
            uch4=' ';
        }
        private void refresh(){

            i = r.nextInt(10);

            t = r.nextInt(2);
            ch2 = (t==0)?'+':(i==1)?'-':(i==2)?'*':'/';

            i3 = r.nextInt(9)+1;
            i2 = (ch2=='/')?(i3*r.nextInt(10)):r.nextInt(10);
            t = r.nextInt(4);
            ch1 = (t==0)?'+':'-';
            t1=i;
            t2=i2;
            t3=i3;
            ans = 0;
            switch (ch1){
                case '-':
                    i2=-i2;
                    break;
            }
            switch(ch2){
                case '*':
                    ans=i2*i3;
                    break;
                case '/':
                    ans=i2/i3;
                    break;
                case '+':
                    ans=i2+i3;
                    break;
                case '-':
                    ans=i2-i3;
                    break;
            }
            switch (ch1){
                case '-':
                    ans=-ans;
                    break;
            }
            ans=ans+i;

            gstvsl.setText(uch2=='+'?"+":uch2=='-'?"-":uch2=='*'?"*":uch2=='/'?"/":" ? ");
            gstvsr.setText(uch4=='+'?"+":uch4=='-'?"-":uch4=='*'?"*":uch4=='/'?"/":" ? ");
            n2 = abs(i2);
            n = abs(i);
            n3 = abs(i3);
            gstvdm.setText('('+n2.toString());
            gstvdl.setText(n.toString());
            gstvdr.setText(n3.toString()+')');
            gstvda.setText(ans.toString());

        }
        public boolean GetResult(){
            return result;
        }
        @Override
        public void run() {
            switch (action){
                case 1:
                    refresh();
                    break;
                case 2:
                    clear();
                    break;
                case 3:
                    check();
                    break;
                case 4:
                    writecorrect();
                    break;
                case 5:
                    reset();
                    break;

            }
            try {
                locked=true;
                this.sleep(delay);
                locked=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                locked=false;
            }
            Toast.makeText(getApplicationContext(), "Goodbye form thread "+this.getId(), Toast.LENGTH_SHORT).show();
        }
    }
    boolean locked= false;
    int action;
    Integer score=0;
    Integer level=0;
    TextView gstvdl;
    TextView gstvdm;
    TextView gstvdr;
    TextView gstvda;
    TextView gstvsl;
    TextView gstvsr;
    TextView gstvscore;
    TextView gstvlevel;
    RadioGroup rg,rg2,rg3,rg4,rg5;
    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_signs);
        Button tmpb = findViewById(R.id.tmpb);
        tmpb.setOnClickListener(this);
        r = new Random(level);
        gstvscore= findViewById(R.id.textScore);
        gstvlevel= findViewById(R.id.textLevel);
        gstvdl = findViewById(R.id.gstvdl);
        gstvdm = findViewById(R.id.gstvdm);
        gstvdr = findViewById(R.id.gstvdr);
        gstvda = findViewById(R.id.gstvda);
        gstvsl = findViewById(R.id.gstvsl);
        gstvsr = findViewById(R.id.gstvsr);
        rg2 = findViewById(R.id.gnrg2);
        rg2.setOnCheckedChangeListener(this);
        rg4 = findViewById(R.id.gnrg4);
        rg4.setOnCheckedChangeListener(this);
        action = 1;
        new Background().run();
    }

    Integer t,i,i2,i3,n,n2,n3,ans,t1,t2,t3;
    char ch1,ch2,uch2,uch4;

    @Override
   public void onClick(View v) {
        if(locked) return;
        switch (v.getId()){
            case (R.id.tmpb):
                action=3;
                Background bg = new Background(200);
                bg.run();
                if(!bg.GetResult()){action=4;new Background().run();} else {gstvscore.setText("Score: "+(++score).toString()); if (score%10==0){gstvlevel.setText("Level: "+(++level).toString());}}
                action=2;
                new Background().run();
                action=1;
                new Background().run();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (locked) return;
        switch (group.getId()){
            case R.id.gnrg2:
                switch (checkedId){
                    case R.id.gnrg21:
                        uch2='+';
                        //Toast.makeText(getApplicationContext(),"uch2 = "+uch2, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                    case R.id.gnrg22:
                        uch2='-';
                        //Toast.makeText(getApplicationContext(),"uch2 = "+uch2, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                    case R.id.gnrg23:
                        uch2='*';
                        //Toast.makeText(getApplicationContext(),"uch2 = "+uch2, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                    case R.id.gnrg24:
                        uch2='/';
                        //Toast.makeText(getApplicationContext(),"uch2 = "+uch2, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                }
                break;
            case R.id.gnrg4:
                switch (checkedId){
                    case R.id.gnrg41:
                        uch4='+';
                        //Toast.makeText(getApplicationContext(),"uch4 = "+uch4, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                    case R.id.gnrg42:
                        uch4='-';
                        //Toast.makeText(getApplicationContext(),"uch4 = "+uch4, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                    case R.id.gnrg43:
                        uch4='*';
                        //Toast.makeText(getApplicationContext(),"uch4 = "+uch4, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                    case R.id.gnrg44:
                        uch4='/';
                        //Toast.makeText(getApplicationContext(),"uch4 = "+uch4, Toast.LENGTH_LONG).show();
                        action=5;
                        new Background().run();
                        break;
                }
                break;
        }
    }
}
