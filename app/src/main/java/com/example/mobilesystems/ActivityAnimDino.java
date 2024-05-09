package com.example.mobilesystems;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;

import java.util.Objects;

public class ActivityAnimDino extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_dino);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Получаем ссылки на ImageView для каждой анимации
        ImageView jumpImageView = findViewById(R.id.jumpImageView);
        ImageView runImageView = findViewById(R.id.runImageView);
        ImageView walkImageView = findViewById(R.id.walkImageView);
        ImageView deadImageView = findViewById(R.id.deadImageView);

        // Запускаем анимации для каждого ImageView
        startAnimation(jumpImageView, R.drawable.jump);
        startAnimation(runImageView, R.drawable.run);
        startAnimation(walkImageView, R.drawable.walk);
        startAnimation(deadImageView, R.drawable.dead);
    }

    // Метод для запуска анимации в указанном ImageView
    private void startAnimation(ImageView imageView, int animationResource) {
        AnimationDrawable animation = (AnimationDrawable) getResources().getDrawable(animationResource);
        imageView.setImageDrawable(animation);
        animation.start();
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