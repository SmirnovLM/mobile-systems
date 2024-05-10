package com.example.lab9;

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

        // Находим кнопки в макете по их ID
        Button buttonActivityDrawHouse = findViewById(R.id.buttonActivityDrawHouse);
        Button buttonActivityAnimDino = findViewById(R.id.buttonActivityAnimDino);
        Button buttonActivityParticle = findViewById(R.id.buttonActivityParticle);
        Button buttonActivityParticleExplosion = findViewById(R.id.buttonActivityParticleExplosion);
        Button buttonActivityParticleFontan = findViewById(R.id.buttonActivityParticleFontan);
        Button buttonActivityMP3Player = findViewById(R.id.buttonActivityMP3Player);
        Button buttonActivityVideoPlayer = findViewById(R.id.buttonActivityVideoPlayer);

        // Назначаем обработчики событий для кнопок
        buttonActivityDrawHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityDrawHouse.class));
            }
        });

        buttonActivityAnimDino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityAnimDino.class));
            }
        });

        buttonActivityParticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityParticle.class));
            }
        });

        buttonActivityParticleExplosion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityParticleExplosion.class));
            }
        });

        buttonActivityParticleFontan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityParticleFontan.class));
            }
        });

        buttonActivityMP3Player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityMP3Player.class));
            }
        });

        buttonActivityVideoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityVideoPlayer.class));
            }
        });
    }
}