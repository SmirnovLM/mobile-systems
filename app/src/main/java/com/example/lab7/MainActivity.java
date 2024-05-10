package com.example.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button trafficLightsButton, helloWorldButton, stopwatchButton, pageStackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trafficLightsButton = findViewById(R.id.button_traffic_lights);
        helloWorldButton = findViewById(R.id.button_hello_world);
        stopwatchButton = findViewById(R.id.button_stopwatch);
        pageStackButton = findViewById(R.id.button_pagestack);

        // Установка слушателей для кнопок
        trafficLightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие активности "TrafficLightsActivity"
                startActivity(new Intent(MainActivity.this, ActivityTrafficLights.class));
            }
        });

        helloWorldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие активности "HelloWorldActivity"
                startActivity(new Intent(MainActivity.this, ActivityHelloWorld.class));
            }
        });

        stopwatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие активности "StopwatchActivity"
                startActivity(new Intent(MainActivity.this, ActivityStopwatch.class));
            }
        });

        pageStackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие активности "PageStackActivity"
                startActivity(new Intent(MainActivity.this, ActivityPageStack.class));
            }
        });

    }
}