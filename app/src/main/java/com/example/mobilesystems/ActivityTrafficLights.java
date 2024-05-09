package com.example.mobilesystems;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ActivityTrafficLights extends AppCompatActivity {
    private int[] colors = {R.drawable.red_light, R.drawable.yellow_light, R.drawable.green_light, R.drawable.yellow_light};
    private int[] durations = {3000, 500, 5000, 500}; // Время в миллисекундах для каждого сигнала (красный, желтый, зеленый)
    private int currentIndex = 0;
    private Handler handler = new Handler();
    private TrafficLight redLight, yellowLight, greenLight;
    private ImageView humanIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_lights);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Находим ImageView для светофора
        redLight = new TrafficLight(findViewById(R.id.red_light));
        yellowLight = new TrafficLight(findViewById(R.id.yellow_light));
        greenLight = new TrafficLight(findViewById(R.id.green_light));

        humanIcon = findViewById(R.id.human_icon);

        // Показываем первый цвет сразу после запуска
        changeColor();

        // Начать автоматическую смену цветов через задержку
        startColorChange();
    }

    private void startColorChange() {
        handler.postDelayed(colorChangeRunnable, durations[currentIndex]);
    }

    private Runnable colorChangeRunnable = new Runnable() {
        @Override
        public void run() {
            currentIndex = (currentIndex + 1) % colors.length; // Обновляем currentIndex для следующего цвета
            changeColor(); // Изменяем цвет
            handler.postDelayed(this, durations[currentIndex]); // Запускаем следующую задержку
        }
    };

    private void changeColor() {
        // Устанавливаем прозрачность по умолчанию для всех сигналов
        redLight.setActive(false);
        yellowLight.setActive(false);
        greenLight.setActive(false);

        // Определяем, какой сигнал показать и устанавливаем прозрачность
        switch (currentIndex) {
            case 0:
                redLight.setColor(colors[currentIndex]);
                redLight.setActive(true);
                break;
            case 1:
                yellowLight.setColor(colors[currentIndex]);
                yellowLight.setActive(true);
                break;
            case 2:
                animateHumanIcon();
                greenLight.setColor(colors[currentIndex]);
                greenLight.setActive(true);
                break;
            case 3:
                //humanIcon.setTranslationX(0f);
                yellowLight.setColor(colors[currentIndex]);
                yellowLight.setActive(true);
                break;
        }
    }

    private void animateHumanIcon() {
        // Получаем ширину экрана
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;

        // Получаем высоту экрана
        int screenHeight = displayMetrics.heightPixels;

        // Анимация для перемещения иконки человечка вправо
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(humanIcon, "translationX", 0f, screenWidth - humanIcon.getWidth()); // перемещение на ширину экрана минус ширина иконки
        animatorX.setDuration(5000); // Длительность анимации - 5 секунд

        animatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // Возвращаем мотоциклиста в исходное положение без анимации
                humanIcon.setTranslationX(0f);
            }
        });

        animatorX.start();
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