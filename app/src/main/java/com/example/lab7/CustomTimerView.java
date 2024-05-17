package com.example.lab7;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class CustomTimerView extends LinearLayout {
    private TextView hoursTextView, minutesTextView, secondsTextView;
    private Handler handler;


    private ImageView secondHandImageView;
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
        secondHandImageView = findViewById(R.id.secondHandImageView);


        handler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - startTime;
                int seconds = (int) (elapsedTime / 1000) % 60; // Получаем количество секунд
                float degrees = seconds * 6; // Каждая секунда увеличивает угол на 6 градусов

                // Поворачиваем стрелку
                secondHandImageView.setRotation(degrees);

                // Обновляем таймер
                int minutes = (int) (elapsedTime / 60000) % 60; // Получаем количество минут
                int hours = (int) (elapsedTime / 3600000); // Получаем количество часов

                updateTimer(hours, minutes, seconds);

                if (isRunning) {
                    handler.postDelayed(this, 1000); // Обновление таймера каждую секунду
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
        updateTimer(0, 0, 0);
        pausedTime = 0;
        secondHandImageView.setRotation(0);
    }

    private void updateTimer(int hours, int minutes, int seconds) {
        hoursTextView.setText(String.format(Locale.getDefault(), "%02d", hours));
        minutesTextView.setText(String.format(Locale.getDefault(), "%02d", minutes));
        secondsTextView.setText(String.format(Locale.getDefault(), "%02d", seconds));
    }


}
