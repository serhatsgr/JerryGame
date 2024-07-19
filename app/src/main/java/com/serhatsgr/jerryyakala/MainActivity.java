package com.serhatsgr.jerryyakala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textScore;
    TextView textTime;
    int score =0;

    int counter=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textScore =findViewById(R.id.textScore);
        textTime=findViewById(R.id.textTime);

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText("Time:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }

    public void increaseScore(View view){
        score++;
        textScore.setText("Score:"+ score);
    }

}