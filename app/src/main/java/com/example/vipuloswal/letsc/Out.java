package com.example.vipuloswal.letsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Out extends AppCompatActivity {

    TextView score,s;
    DatabaseHelper d;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_out);


        t1=(TextView)findViewById(R.id.set1);
        t2=(TextView)findViewById(R.id.set2);
        t3=(TextView)findViewById(R.id.set3);
        t4=(TextView)findViewById(R.id.set4);
        t5=(TextView)findViewById(R.id.set5);
        t6=(TextView)findViewById(R.id.set6);
        t7=(TextView)findViewById(R.id.set7);
        t8=(TextView)findViewById(R.id.set8);
        final Animation a1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        t1.startAnimation(a1);
        t2.startAnimation(a1);
        t3.startAnimation(a1);
        t4.startAnimation(a1);
        t5.startAnimation(a1);
        t6.startAnimation(a1);
        t7.startAnimation(a1);
        t8.startAnimation(a1);

        Bundle b1=getIntent().getExtras();
        ArrayList<String> words=b1.getStringArrayList("wordsdone");
        for(int i=words.size();i<8;i++)
            words.add(" ");
        t1.setText(words.get(0));
        t2.setText(words.get(1));
        t3.setText(words.get(2));
        t4.setText(words.get(3));
        t5.setText(words.get(4));
        t6.setText(words.get(5));
        t7.setText(words.get(6));
        t8.setText(words.get(7));
//        RelativeLayout r=(RelativeLayout)findViewById(R.id.rl);
//        r.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Out.this,Play.class);
//                startActivity(i);
//            }
//        });
        Button play=(Button)findViewById(R.id.play);
        score=(TextView)findViewById(R.id.score);
        s=(TextView)findViewById(R.id.s);
        s.setText(b1.getString("score"));
        int i=Integer.parseInt(b1.getString("score"));
        d=new DatabaseHelper(this);
        int ss=d.getData("HS");
        //s.setText(""+);
        if(i>ss) {
            score.setText("Highest Score");
            d.update("HS",i);
        }
        d.update("Game",d.getData("Game")+1);
        d.update("Word",d.getData("Word")+(i/10));
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Out.this,Play.class);
                Out.this.finish();
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(Out.this,Home.class);
        Out.this.finish();
        startActivity(i);
    }
}
