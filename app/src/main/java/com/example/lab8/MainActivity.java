package com.example.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonAddCounterClass;
    private Button buttonAddCounterClassMetaObj;
    private Button buttonAddStringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddCounterClass = findViewById(R.id.buttonAddCounterClass);
        buttonAddCounterClassMetaObj = findViewById(R.id.buttonAddCounterClassMetaObj);
        buttonAddStringList = findViewById(R.id.buttonAddStringList);

        buttonAddCounterClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityCounterClass.class);
                startActivity(intent);
            }
        });

        buttonAddCounterClassMetaObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityCounterClassMetaObj.class);
                startActivity(intent);
            }
        });

        buttonAddStringList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityStringList.class);
                startActivity(intent);
            }
        });
    }
}