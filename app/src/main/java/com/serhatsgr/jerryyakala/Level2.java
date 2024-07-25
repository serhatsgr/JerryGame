package com.serhatsgr.jerryyakala;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    TextView textScore, textTime;
    int score = 0;

    ImageView img13;

    ImageView[] img = new ImageView[12];

    Random rnd = new Random();

    private Handler handler1, handler2,handler3;
    private Runnable runnable1, runnable2, runnable3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
        initializeViews();
        startGame();

    }

    private void initializeViews() {
        textScore = findViewById(R.id.textScore);
        textTime = findViewById(R.id.textTime);

        for (int i = 0; i < img.length; i++) {
            int resId = getResources().getIdentifier("imageView" + (i + 1), "id", getPackageName());
            img[i] = findViewById(resId);
        }
    }

    public void startGame(){

        img13 = findViewById(R.id.imageView13);

        img13.setVisibility(View.INVISIBLE);


        hideAllImages();
        randomImg();

        handler1 = new Handler();
        handler2 = new Handler();
        handler3 = new Handler();


        runnable1 = new Runnable() {
            @Override
            public void run() {
                hideAllImages();
                randomImg();
                handler1.postDelayed(this, 1000);
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                img13.setVisibility(View.VISIBLE);
                handler2.postDelayed(this, 4000);
            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {
                img13.setVisibility(View.INVISIBLE);
                handler3.postDelayed(this, 5500);
            }
        };


        handler1.post(runnable1);
        handler2.postDelayed(runnable2, 4000);
        handler3.postDelayed(runnable3, 5500);

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                handler1.removeCallbacks(runnable1);
                handler2.removeCallbacks(runnable2);
                hideAllImages();
            }
        }.start();

    }

    public void increaseScore(View view) {
        score++;
        textScore.setText("Score: " + score);
    }

    public void randomImg() {
        int randomNumber = rnd.nextInt(12);
        img[randomNumber].setVisibility(View.VISIBLE);
    }

    public void hideAllImages() {
        for (ImageView imageView : img) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
