package com.example.vipuloswal.letsc;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Play extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play);
        t=(TextView)findViewById(R.id.count);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {
                t.setText(""+(l/1000));
            }

            @Override
            public void onFinish() {
                t.setText("Ready...");
                Intent i=new Intent(Play.this,Game.class);
                Play.this.finish();
                startActivity(i);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Play.this.finish();
    }
}
