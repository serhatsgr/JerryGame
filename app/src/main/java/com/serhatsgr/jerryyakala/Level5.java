package com.serhatsgr.jerryyakala;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Rect;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.animation.ValueAnimator;

import java.util.Random;

public class Level5 extends AppCompatActivity {

    TextView textScore, textTime;
    ImageView imageJerry, imageTom, imageBonusJerry, imageBonusTime;
    Button buttonStart;

    boolean isTomVisible = false;
    boolean isGameRunning = false;
    boolean isGamePaused = false;

    int score = 0;
    long timeLeftInMillis = 20000;

    int maxX, maxY;

    Random rnd = new Random();
    Handler handler, tomHandler, bonusJerryHandler, bonusTimeHandler;
    Runnable runnable, tomRunnable, bonusJerryRunnable, bonusTimeRunnable;

    CountDownTimer countDownTimer;

    MediaPlayer mediaPlayer;
    Vibrator vibrator;

    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);

        imageTom = findViewById(R.id.imageSpike);
        textScore = findViewById(R.id.textScore);
        textTime = findViewById(R.id.textTime);
        imageJerry = findViewById(R.id.imageJerry);
        imageBonusJerry = findViewById(R.id.imageBonusJerry);
        imageBonusTime = findViewById(R.id.imageBonusTime);
        buttonStart = findViewById(R.id.buttonStart);

        rootLayout = findViewById(R.id.rootLayout);

        mediaPlayer = MediaPlayer.create(Level5.this, R.raw.clickjerry);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        rootLayout.post(() -> {
            maxX = rootLayout.getWidth() - imageJerry.getWidth();
            maxY = rootLayout.getHeight() - imageJerry.getHeight();
        });

        buttonStart.setOnClickListener(v -> {
            int colorFrom = android.graphics.Color.parseColor("#FFA726"); // turuncu
            int colorTo = android.graphics.Color.parseColor("#FFF176"); // sarımsı
            ValueAnimator colorAnim = ValueAnimator.ofObject(new android.animation.ArgbEvaluator(), colorFrom, colorTo, colorFrom);
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
        hideBonusJerry();
        hideBonusTime();
        randomJerryPosition();

        if (!isGamePaused) {
            score = 0;
            textScore.setText("Score: " + score);
        }

        imageJerry.setOnClickListener(v -> {
            if (isTomVisible) {
                vibrate();
                score -= 3;
            } else {
                playClickSound();
                score++;
            }
            textScore.setText("Score: " + score);
            imageJerry.setVisibility(View.INVISIBLE);
        });

        imageBonusJerry.setOnClickListener(v -> {
            score += 3;
            textScore.setText("Score: " + score);
            imageBonusJerry.setVisibility(View.INVISIBLE);
        });

        imageBonusTime.setOnClickListener(v -> {
            // Mevcut zamanlayıcıyı durdur
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            // 5 saniye ekle
            timeLeftInMillis += 5000;

            // Yeni zamanlayıcıyı başlat
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    textTime.setText("Süre: " + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    textScore.setText("Score: 0");
                    textTime.setText("Süre bitti!");
                    handler.removeCallbacks(runnable);
                    if (tomHandler != null) tomHandler.removeCallbacks(tomRunnable);
                    if (bonusJerryHandler != null) bonusJerryHandler.removeCallbacks(bonusJerryRunnable);
                    if (bonusTimeHandler != null) bonusTimeHandler.removeCallbacks(bonusTimeRunnable);
                    hideJerry();
                    hideTom();
                    hideBonusJerry();
                    hideBonusTime();

                    if (score >= 25) {
                        showLevelSuccessDialog();
                    } else {
                        showGameOverDialog();
                    }

                    isGameRunning = false;
                    buttonStart.setText("Yakala");
                    timeLeftInMillis = 20000;
                }
            }.start();

            // Görselliği gizle
            imageBonusTime.setVisibility(View.INVISIBLE);
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
        startBonusJerryAppearance();
        startBonusTimeAppearance();

        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                textTime.setText("Süre: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                textScore.setText("Score: 0");
                textTime.setText("Süre bitti!");
                handler.removeCallbacks(runnable);
                if (tomHandler != null) tomHandler.removeCallbacks(tomRunnable);
                if (bonusJerryHandler != null) bonusJerryHandler.removeCallbacks(bonusJerryRunnable);
                if (bonusTimeHandler != null) bonusTimeHandler.removeCallbacks(bonusTimeRunnable);
                hideJerry();
                hideTom();
                hideBonusJerry();
                hideBonusTime();

                if (score >= 25) {
                    showLevelSuccessDialog();
                } else {
                    showGameOverDialog();
                }

                isGameRunning = false;
                buttonStart.setText("Başlat");
                timeLeftInMillis = 20000;
            }
        }.start();
    }

    private void pauseGame() {
        if (countDownTimer != null) countDownTimer.cancel();
        if (handler != null) handler.removeCallbacks(runnable);
        if (tomHandler != null) tomHandler.removeCallbacks(tomRunnable);
        if (bonusJerryHandler != null) bonusJerryHandler.removeCallbacks(bonusJerryRunnable);
        if (bonusTimeHandler != null) bonusTimeHandler.removeCallbacks(bonusTimeRunnable);
        hideJerry();
        hideTom();
        hideBonusJerry();
        hideBonusTime();
    }

    private void randomJerryPosition() {
        int minY = 300;
        int maxY = 1700;
        int x, y;
        do {
            y = rnd.nextInt(maxY - minY) + minY;
            x = rnd.nextInt(rootLayout.getWidth() - imageJerry.getWidth());
            imageJerry.setX(x);
            imageJerry.setY(y);
        } while (isOverlapping(imageJerry, imageBonusJerry) || isOverlapping(imageJerry, imageBonusTime));
        imageJerry.setVisibility(View.VISIBLE);
    }

    private void randomBonusJerryPosition() {
        int minY = 300;
        int maxY = 1700;
        int x, y;
        do {
            y = rnd.nextInt(maxY - minY) + minY;
            x = rnd.nextInt(rootLayout.getWidth() - imageBonusJerry.getWidth());
            imageBonusJerry.setX(x);
            imageBonusJerry.setY(y);
        } while (isOverlapping(imageBonusJerry, imageJerry) || isOverlapping(imageBonusJerry, imageBonusTime));
        imageBonusJerry.setVisibility(View.VISIBLE);
        new Handler().postDelayed(this::hideBonusJerry, 1000);
    }

    private void randomBonusTimePosition() {
        int minY = 300;
        int maxY = 1700;
        int x, y;
        do {
            y = rnd.nextInt(maxY - minY) + minY;
            x = rnd.nextInt(rootLayout.getWidth() - imageBonusTime.getWidth());
            imageBonusTime.setX(x);
            imageBonusTime.setY(y);
        } while (isOverlapping(imageBonusTime, imageJerry) || isOverlapping(imageBonusTime, imageBonusJerry));
        imageBonusTime.setVisibility(View.VISIBLE);
        new Handler().postDelayed(this::hideBonusTime, 1000);
    }

    private boolean isOverlapping(ImageView img1, ImageView img2) {
        int[] loc1 = new int[2];
        int[] loc2 = new int[2];
        img1.getLocationOnScreen(loc1);
        img2.getLocationOnScreen(loc2);

        Rect rect1 = new Rect(loc1[0], loc1[1],
                loc1[0] + img1.getWidth(), loc1[1] + img1.getHeight());
        Rect rect2 = new Rect(loc2[0], loc2[1],
                loc2[0] + img2.getWidth(), loc2[1] + img2.getHeight());

        return Rect.intersects(rect1, rect2);
    }

    private void hideJerry() {
        imageJerry.setVisibility(View.INVISIBLE);
    }

    private void hideBonusJerry() {
        imageBonusJerry.setVisibility(View.INVISIBLE);
    }

    private void hideBonusTime() {
        imageBonusTime.setVisibility(View.INVISIBLE);
    }

    private void startTomAppearance() {
        tomHandler = new Handler();
        tomRunnable = new Runnable() {
            @Override
            public void run() {
                showTomTemporarily();
                tomHandler.postDelayed(this, rnd.nextInt(4000) + 2000);
            }
        };
        tomHandler.post(tomRunnable);
    }

    private void showTomTemporarily() {
        isTomVisible = true;
        imageTom.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            imageTom.setVisibility(View.INVISIBLE);
            isTomVisible = false;
        }, 1000);
    }

    private void hideTom() {
        imageTom.setVisibility(View.INVISIBLE);
        isTomVisible = false;
    }

    private void startBonusJerryAppearance() {
        bonusJerryHandler = new Handler();
        bonusJerryRunnable = () -> {
            if (rnd.nextInt(100) < 45) {
                randomBonusJerryPosition();
            }
            bonusJerryHandler.postDelayed(bonusJerryRunnable, 3000);
        };
        bonusJerryHandler.post(bonusJerryRunnable);
    }

    private void startBonusTimeAppearance() {
        bonusTimeHandler = new Handler();
        bonusTimeRunnable = () -> {
            if (rnd.nextInt(100) < 30) {
                randomBonusTimePosition();
            }
            bonusTimeHandler.postDelayed(bonusTimeRunnable, 4000);
        };
        bonusTimeHandler.post(bonusTimeRunnable);
    }

    private void vibrate() {
        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(200);
            }
        }
    }

    private void playClickSound() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    private void showLevelSuccessDialog() {
        Dialog dialog = new Dialog(Level5.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_level_success, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        TextView textMessage = dialogView.findViewById(R.id.textMessage);
        Button buttonNextLevel = dialogView.findViewById(R.id.buttonNextLevel);
        Button buttonReplay = dialogView.findViewById(R.id.buttonReplay);

        textTitle.setText("Tebrikler!");
        textMessage.setText("5. seviyeyi başarıyla tamamladın!\nSkorun: " + score);
        buttonNextLevel.setText("Ana Menüye Dön");
        buttonReplay.setText("Tekrar Oyna");

        buttonNextLevel.setOnClickListener(v -> {
            finish();
            dialog.dismiss();
        });

        buttonReplay.setOnClickListener(v -> {
            score = 0;
            textScore.setText("Score: 0");
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showGameOverDialog() {
        Dialog dialog = new Dialog(Level5.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_game_over, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        TextView textMessage = dialogView.findViewById(R.id.textMessage);
        Button buttonRetry = dialogView.findViewById(R.id.buttonRetry);
        Button buttonExit = dialogView.findViewById(R.id.buttonExit);

        textTitle.setText("Oyun Bitti");
        textMessage.setText("25 skora ulaşamadın. Skorun: " + score);
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
