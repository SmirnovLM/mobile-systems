package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab5.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonActivityPageStack = findViewById(R.id.buttonActivityPageStack);
        Button buttonActivityTextPicker = findViewById(R.id.buttonActivityTextPicker);
        Button buttonActivityDataPicker = findViewById(R.id.buttonActivityDataPicker);
        Button buttonActivityTimePicker = findViewById(R.id.buttonActivityTimePicker);
        Button buttonActivityWeeklyTasks = findViewById(R.id.buttonActivityWeeklyTasks);
        Button buttonActivityWebView = findViewById(R.id.buttonActivityWebView);
        Button buttonActivityListMenu = findViewById(R.id.buttonActivityListMenu);
        Button buttonActivityUpDwMenu = findViewById(R.id.buttonActivityUpDwMenu);
        Button buttonActivityNotificationClicker = findViewById(R.id.buttonActivityNotificationClicker);

        buttonActivityPageStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityPageStack.class));
            }
        });

        buttonActivityTextPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityTextPicker.class));
            }
        });

        buttonActivityDataPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityDataPicker.class));
            }
        });

        buttonActivityTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityTimePicker.class));
            }
        });

        buttonActivityWeeklyTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityWeeklyTasks.class));
            }
        });

        buttonActivityWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityWebView.class));
            }
        });

        buttonActivityListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityListMenu.class));
            }
        });

        buttonActivityUpDwMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityUpDwMenu.class));
            }
        });

        buttonActivityNotificationClicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityNotificationClicker.class));
            }
        });
    }
}