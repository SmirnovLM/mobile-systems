package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonActColorRect = findViewById(R.id.buttonActColorRect);
        Button buttonActColorRectWeb = findViewById(R.id.buttonActColorRectWeb);
        Button buttonActNumberRect = findViewById(R.id.buttonActNumberRect);
        Button buttonActCurrencyList = findViewById(R.id.buttonActCurrencyList);
        Button buttonActNoteKeeper = findViewById(R.id.buttonActNoteKeeper);
        Button buttonActConfValue = findViewById(R.id.buttonActConfValue);
        Button buttonActConfGroup = findViewById(R.id.buttonActConfGroup);

        buttonActColorRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityColorRectangles.class));
            }
        });

        buttonActColorRectWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityColorRectanglesWeb.class));
            }
        });

        buttonActNumberRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityNumberRectangles.class));
            }
        });

        buttonActCurrencyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityCurrencyList.class));
            }
        });

        buttonActNoteKeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityNoteKeeper.class));
            }
        });

        buttonActConfValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityConfigurationValue.class));
            }
        });

        buttonActConfGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityConfigurationGroup.class));
            }
        });

    }
}