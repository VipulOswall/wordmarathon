package com.example.vipuloswal.letsc;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    ImageButton b;
    DatabaseHelper d;
    TextView bestscore,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        b=(ImageButton)findViewById(R.id.play);
        bestscore=(TextView)findViewById(R.id.bestscore);
        score=(TextView)findViewById(R.id.score);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Play.class);
                Home.this.finish();
                startActivity(i);
            }
        });
        d=new DatabaseHelper(this);
        d.getWritableDatabase();
        d.create();
        int i=d.getData("HS");
        if(i==0)
            bestscore.setText("Welcome");
        else
            score.setText(""+i);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //Intent i=new Intent(Home.this,Home.class);
        Home.this.finish();
        //startActivity(i);
    }
}
