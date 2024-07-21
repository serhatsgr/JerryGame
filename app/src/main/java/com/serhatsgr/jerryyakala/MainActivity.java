package com.serhatsgr.jerryyakala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textScore;
    TextView textTime;
    int score = 0;

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    ImageView[] img = new ImageView[9];

    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textScore = findViewById(R.id.textScore);
        textTime = findViewById(R.id.textTime);
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img8 = findViewById(R.id.imageView8);
        img9 = findViewById(R.id.imageView9);

        img[0] = img1;
        img[1] = img2;
        img[2] = img3;
        img[3] = img4;
        img[4] = img5;
        img[5] = img6;
        img[6] = img7;
        img[7] = img8;
        img[8] = img9;

        hideAllImages();
        randomImg();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Timer bitince yapılacaklar
            }
        }.start();
    }

    public void increaseScore(View view) {
        score++;
        textScore.setText("Score: " + score);
        hideAllImages();
        randomImg();
    }

    public void randomImg() {
        int randomNumber = rnd.nextInt(9);
        img[randomNumber].setVisibility(View.VISIBLE);
    }

    public void hideAllImages() {
        for (ImageView imageView : img) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
