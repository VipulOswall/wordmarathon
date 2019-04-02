package com.example.vipuloswal.letsc;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2;
    ProgressBar p;
    ArrayList<String> str;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        Animation a= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        t1.startAnimation(a);
        t2.startAnimation(a);
        p=(ProgressBar)findViewById(R.id.p);
        //p.getProgressDrawable().setColorFilter(
              //  Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        str=new ArrayList<>();
        str.add("#F7FE00");
        str.add("#FFF");
        str.add("#ff4081");
        new CountDownTimer(3000,500) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
            Intent i=new Intent(MainActivity.this,Home.class);
                MainActivity.this.finish();
            startActivity(i);
            }
        }.start();
    }
}
