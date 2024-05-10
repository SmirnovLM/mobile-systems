package com.example.lab7;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class CustomTimerView extends LinearLayout {
    private TextView hoursTextView, minutesTextView, secondsTextView, millisecondsTextView;
    private Handler handler;
    private Runnable timerRunnable;
    private long startTime;
    private long pausedTime;
    private boolean isRunning;

    public CustomTimerView(Context context) {
        super(context);
        init(context);
    }

    public CustomTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_timer_view, this);

        hoursTextView = findViewById(R.id.hoursTextView);
        minutesTextView = findViewById(R.id.minutesTextView);
        secondsTextView = findViewById(R.id.secondsTextView);
        millisecondsTextView = findViewById(R.id.millisecondsTextView);

        handler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - startTime;
                int seconds = (int) (elapsedTime / 1000);
                int minutes = seconds / 60;
                int hours = minutes / 60;
                int milliseconds = (int) (elapsedTime % 1000);

                seconds %= 60;
                minutes %= 60;

                updateTimer(hours, minutes, seconds, milliseconds);

                if (isRunning) {
                    handler.postDelayed(this, 100); // Обновление таймера каждые 100 миллисекунд
                }
            }
        };
    }

    public void startTimer() {
        if (!isRunning) {
            if (pausedTime > 0) {
                startTime += (System.currentTimeMillis() - pausedTime);
                pausedTime = 0;
            } else {
                startTime = System.currentTimeMillis();
            }
            handler.post(timerRunnable);
            isRunning = true;
        }
    }

    public void stopTimer() {
        if (isRunning) {
            handler.removeCallbacks(timerRunnable);
            isRunning = false;
            pausedTime = System.currentTimeMillis();
        }
    }

    public void resetTimer() {
        stopTimer();
        updateTimer(0, 0, 0, 0);
        pausedTime = 0;
    }

    private void updateTimer(int hours, int minutes, int seconds, int milliseconds) {
        hoursTextView.setText(String.format(Locale.getDefault(), "%02d", hours));
        minutesTextView.setText(String.format(Locale.getDefault(), "%02d", minutes));
        secondsTextView.setText(String.format(Locale.getDefault(), "%02d", seconds));
        millisecondsTextView.setText(String.format(Locale.getDefault(), "%03d", milliseconds));
    }
}
