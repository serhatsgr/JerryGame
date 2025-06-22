package com.serhatsgr.jerryyakala;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.app.Dialog;
import android.view.LayoutInflater;


public class Level3 extends AppCompatActivity {

    TextView textScore, textTime;
    ImageView imageJerry;
    ImageView imageTom;
    Button buttonStart;

    boolean isTomVisible = false;
    boolean isGameRunning = false;
    boolean isGamePaused = false;

    int score = 0;
    long timeLeftInMillis = 20000;

    int maxX, maxY;

    Random rnd = new Random();
    Handler handler, tomHandler;
    Runnable runnable, tomRunnable;

    CountDownTimer countDownTimer;

    MediaPlayer mediaPlayer;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        imageTom = findViewById(R.id.imageSpike);
        textScore = findViewById(R.id.textScore);
        textTime = findViewById(R.id.textTime);
        imageJerry = findViewById(R.id.imageJerry);
        buttonStart = findViewById(R.id.buttonStart);

        RelativeLayout rootLayout = findViewById(R.id.rootLayout);

        mediaPlayer=MediaPlayer.create(Level3.this, R.raw.clickjerry);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        rootLayout.post(() -> {
            maxX = rootLayout.getWidth() - imageJerry.getWidth();
            maxY = rootLayout.getHeight() - imageJerry.getHeight();
        });

        buttonStart.setOnClickListener(v -> {
            int colorFrom = android.graphics.Color.parseColor("#FFA726"); // turuncu
            int colorTo = android.graphics.Color.parseColor("#FFF176"); // sarımsı
            android.animation.ValueAnimator colorAnim = android.animation.ValueAnimator.ofObject(new android.animation.ArgbEvaluator(), colorFrom, colorTo, colorFrom);
            colorAnim.setDuration(350);
            colorAnim.addUpdateListener(anim -> buttonStart.setBackgroundColor((int) anim.getAnimatedValue()));
            colorAnim.start();
            colorAnim.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    buttonStart.setBackgroundResource(R.drawable.button_orange_bg);
                }
            });
            startAndStop(v);
        });
    }

    public void startAndStop(View view) {
        if (!isGameRunning && !isGamePaused) {
            startGame(timeLeftInMillis);
            isGameRunning = true;
            buttonStart.setText("Durdur");
            buttonStart.setBackgroundResource(R.drawable.button_orange_bg);
        } else if (isGameRunning) {
            pauseGame();
            isGameRunning = false;
            isGamePaused = true;
            buttonStart.setText("Devam Et");
            buttonStart.setBackgroundResource(R.drawable.button_orange_bg);
        } else if (isGamePaused) {
            startGame(timeLeftInMillis);
            isGameRunning = true;
            isGamePaused = false;
            buttonStart.setText("Durdur");
            buttonStart.setBackgroundResource(R.drawable.button_orange_bg);
        }
    }

    private void startGame(long time) {
        hideJerry();
        randomJerryPosition();

        if (!isGamePaused) {
            score = 0;
            textScore.setText("Score: " + score);
        }

        imageJerry.setOnClickListener(v -> {

            if (isTomVisible) {
                if (vibrator != null && vibrator.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(200); //
                    }
                }
                score -= 3;
            }

            else {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                }
                score++;
            }
            textScore.setText("Score: " + score);
            imageJerry.setVisibility(View.INVISIBLE);
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                hideJerry();
                randomJerryPosition();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

        startTomAppearance();

        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                textTime.setText("Süre: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textScore.setText("Score: 0");
                textTime.setText("Süre bitti!");
                handler.removeCallbacks(runnable);
                if (tomHandler != null) tomHandler.removeCallbacks(tomRunnable);
                hideJerry();
                hideTom();

                if (score >= 5) {
                    showLevelSuccessDialog();
                } else {
                    showGameOverDialog();
                }

                isGameRunning = false;
                buttonStart.setText("Yakala");
                timeLeftInMillis = 20000;
            }
        }.start();
    }

    private void pauseGame() {
        if (countDownTimer != null) countDownTimer.cancel();
        if (handler != null) handler.removeCallbacks(runnable);
        if (tomHandler != null) tomHandler.removeCallbacks(tomRunnable);
        hideJerry();
        hideTom();
    }

    private void randomJerryPosition() {
        if (maxX > 0 && maxY > 0) {
            int minY = 300;
            int maxY = 1700;
            int y = rnd.nextInt(maxY - minY) + minY;
            int x = rnd.nextInt(findViewById(R.id.rootLayout).getWidth() - imageJerry.getWidth());
            imageJerry.setX(x);
            imageJerry.setY(y);
            imageJerry.setVisibility(View.VISIBLE);
        }
    }

    private void hideJerry() {
        imageJerry.setVisibility(View.INVISIBLE);
    }

    private void showTomTemporarily() {
        isTomVisible = true;
        imageTom.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            imageTom.setVisibility(View.INVISIBLE);
            isTomVisible = false;
        }, 1000); // Tom 1.5 saniye görünsün
    }

    private void startTomAppearance() {
        tomHandler = new Handler();
        tomRunnable = new Runnable() {
            @Override
            public void run() {
                showTomTemporarily();
                int delay = rnd.nextInt(4000) + 2000; // 2-6 saniye arası rastgele tekrar et
                tomHandler.postDelayed(this, delay);
            }
        };
        tomHandler.post(tomRunnable);
    }

    private void hideTom() {
        imageTom.setVisibility(View.INVISIBLE);
        isTomVisible = false;
    }

    private void showLevelSuccessDialog() {
        Dialog dialog = new Dialog(Level3.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_level_success, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        TextView textMessage = dialogView.findViewById(R.id.textMessage);
        Button buttonNextLevel = dialogView.findViewById(R.id.buttonNextLevel);
        Button buttonReplay = dialogView.findViewById(R.id.buttonReplay);

        textTitle.setText("Tebrikler!");
        textMessage.setText("3. seviyeyi başarıyla tamamladın!\nSkorun: " + score);
        buttonNextLevel.setText("Seviye 4'e Geç");
        buttonReplay.setText("Tekrar Oyna");

        buttonNextLevel.setOnClickListener(v -> {
            Intent intent = new Intent(Level3.this, Level4.class);
            startActivity(intent);
            dialog.dismiss();
            finish();
        });

        buttonReplay.setOnClickListener(v -> {
            score = 0;
            textScore.setText("Score: 0");
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showGameOverDialog() {
        Dialog dialog = new Dialog(Level3.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_game_over, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        TextView textMessage = dialogView.findViewById(R.id.textMessage);
        Button buttonRetry = dialogView.findViewById(R.id.buttonRetry);
        Button buttonExit = dialogView.findViewById(R.id.buttonExit);

        textTitle.setText("Oyun Bitti");
        textMessage.setText("5 skora ulaşamadın. Skorun: " + score);
        buttonRetry.setText("Tekrar Dene");
        buttonExit.setText("Çıkış Yap");

        buttonRetry.setOnClickListener(v -> {
            score = 0;
            textScore.setText("Score: 0");
            dialog.dismiss();
        });

        buttonExit.setOnClickListener(v -> {
            finish();
            dialog.dismiss();
        });

        dialog.show();
    }
}
