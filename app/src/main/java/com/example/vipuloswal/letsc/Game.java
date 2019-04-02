package com.example.vipuloswal.letsc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView a,ans1,ans2,ans3,ans4,ans5,ans6,ans7,level,score,time;
    EditText input;
    Button submit,plus,skip;
    String str;
    ArrayList<String> line,words,wordsdone;
    ArrayList<Integer> ii,done;
    ProgressBar p;
    CountDownTimer countdowm;
    int updatedscore=0,anscount=0,up=2000,size,mediacount1=0;
    long timeleft;
    Random r;
    Animation animation;
    MediaPlayer p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        level=(TextView)findViewById(R.id.level);
        time=(TextView)findViewById(R.id.count);
        score=(TextView)findViewById(R.id.score);
        ans1=(TextView)findViewById(R.id.ans1);
        ans2=(TextView)findViewById(R.id.ans2);
        ans3=(TextView)findViewById(R.id.ans3);
        ans4=(TextView)findViewById(R.id.ans4);
        ans5=(TextView)findViewById(R.id.ans5);
        ans6=(TextView)findViewById(R.id.ans6);
        ans7=(TextView)findViewById(R.id.ans7);
        submit=(Button)findViewById(R.id.submit);
        plus=(Button)findViewById(R.id.timeplus);
        skip=(Button)findViewById(R.id.skip);
        input=(EditText)findViewById(R.id.input);
        p=(ProgressBar)findViewById(R.id.progress);
        a=(TextView)findViewById(R.id.a);
        str=getString(R.string.words);
        p1=MediaPlayer.create(this,R.raw.pacman1);
        p2=MediaPlayer.create(this,R.raw.pacman2);
        p1.start();
        p1.setLooping(true);
        r=new Random();
        String lines[]=str.split(" ");
        line=new ArrayList<>();
        ii=new ArrayList<>();
        for(int flag=0;flag<lines.length;flag=flag+1)
        {
            String temp[]=lines[flag].split(":");
            line.add(temp[0]);
            ii.add(Integer.parseInt(temp[1]));
        }
        size=line.size();
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        words=new ArrayList<>();
        done=new ArrayList<>();
        wordsdone=new ArrayList<>();
        //for(int i=0;i<lines.length;i++)line.add(lines[i]);
        next();
        score.setText(""+updatedscore);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputcheck();
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setBackgroundColor(Color.BLACK);
                a.setBackgroundColor(Color.BLACK);
                input.setTextColor(Color.WHITE);
                a.setTextColor(Color.WHITE);
            }
        });
        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()== KeyEvent.KEYCODE_ENTER )
                    inputcheck();
                    return false;
            }
        });

        countdowm=new CountDownTimer(40000,1000)
        {

            @Override
            public void onTick(long l) {
                timeleft=(l/1000);
                time.setText(""+timeleft);
                time.startAnimation(animation);
            }

            @Override
            public void onFinish() {
                Intent i=new Intent(Game.this,Out.class);
                i.putExtra("score",score.getText().toString());
                i.putStringArrayListExtra("wordsdone",wordsdone);
                p1.stop();
                p1.release();
                Game.this.finish();
                startActivity(i);
            }
        }.start();
    }
    public void next()
    {
        int random,got;
            if(up>=500)
                up=up-100;
        while(true) {
            random = r.nextInt(size);
            got = ii.get(random);
            if (got >= up && got <= (up + 500) && !done.contains(random))
                break;
        }
        done.add(random);
        str=line.get(random);
        a.setText(str);
        if(str.equals("do"))str+="o";
        try {
            str = getString(getResources().getIdentifier(str, "string", getPackageName()));
        }catch (Exception e)
        {
            done.clear();
            up=2000;
            next();
        }
        String lines[]=str.split(" ");
        words.clear();
        for(int i=0;i<lines.length;i++)words.add(lines[i]);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder builder=new AlertDialog.Builder(Game.this);
        builder.setTitle("Game Paused").setMessage("Are you sure you want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        countdowm.cancel();
                        Game.this.finish();
                        p1.stop();
                        p1.release();
                        Intent iii=new Intent(Game.this,Home.class);
                        startActivity(iii);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Game.this, "Game Resumed..!!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
    public void inputcheck()
    {
        str=input.getText().toString();
        input.clearFocus();
        input.setText("");
        str=a.getText().toString()+str;
        if (words.contains(str))
        {
            if(wordsdone.contains(str))
                Toast.makeText(this, "Already Done..!!", Toast.LENGTH_SHORT).show();
            else {
                wordsdone.add(str);
                anscount++;
                ans1.setText(ans2.getText());
                ans2.setText(ans3.getText());
                ans3.setText(ans4.getText());
                ans4.setText(ans5.getText());
                ans5.setText(ans6.getText());
                ans6.setText(ans7.getText());
                ans7.setText(str);
                ans1.startAnimation(animation);
                ans2.startAnimation(animation);
                ans3.startAnimation(animation);
                ans4.startAnimation(animation);
                ans5.startAnimation(animation);
                ans6.startAnimation(animation);
                ans7.startAnimation(animation);
                updatedscore += 10;
                score.setText("" + updatedscore);
                if (anscount == 5) {
                    countdowm.cancel();
                    Toast.makeText(this, "Level Up..!!", Toast.LENGTH_SHORT).show();
                    next();
                    anscount = 0;
                    level.setText("" + (Integer.parseInt(level.getText().toString()) + 1));
                    countdowm.start();
                }
            }
        }
        else {
            new CountDownTimer(2000,1000) {
                @Override
                public void onTick(long l) {
                    input.setBackgroundColor(Color.RED);
                    a.setBackgroundColor(Color.RED);
                }

                @Override
                public void onFinish() {
                    input.setBackgroundColor(Color.BLACK);
                    a.setBackgroundColor(Color.BLACK);
                }
            }.start();
        }
    }
}
