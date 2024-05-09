package com.example.mobilesystems;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ActivityStopwatch extends AppCompatActivity {
    private CustomTimerView customTimerView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        customTimerView = findViewById(R.id.customTimerView);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customTimerView.isRunning()) {
                    customTimerView.stopTimer();
                    startButton.setText("Start");
                } else {
                    customTimerView.startTimer();
                    startButton.setText("Stop");
                }
            }
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTimerView.resetTimer();
                startButton.setText("Start");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обрабатываем нажатие на кнопку "Назад"
        if (item.getItemId() == android.R.id.home) {
            // Завершаем текущую активность
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}