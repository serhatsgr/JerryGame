package com.serhatsgr.jerryyakala;
import android.os.CountDownTimer;

public abstract class PauseableCountDownTimer {

    private long millisInFuture;
    private long countDownInterval;
    private long millisRemaining;
    private CountDownTimer countDownTimer;
    private boolean isPaused = true;

    public PauseableCountDownTimer(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        this.millisRemaining = millisInFuture;
    }

    public void start() {
        if (isPaused) {
            isPaused = false;
            countDownTimer = createCountDownTimer(millisRemaining);
            countDownTimer.start();
        }
    }

    public void pause() {
        if (!isPaused && countDownTimer != null) {
            countDownTimer.cancel();
            isPaused = true;
        }
    }

    public void reset() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        millisRemaining = millisInFuture;
        isPaused = true;
    }

    private CountDownTimer createCountDownTimer(final long millisRemaining) {
        return new CountDownTimer(millisRemaining, countDownInterval) {
            public void onTick(long millisUntilFinished) {
                PauseableCountDownTimer.this.millisRemaining = millisUntilFinished;
                onTickAction(millisUntilFinished);
            }

            public void onFinish() {
                onFinishAction();
            }
        };
    }

    public abstract void onTickAction(long millisUntilFinished);
    public abstract void onFinishAction();
}
