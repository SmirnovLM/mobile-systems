package com.example.mobilesystems;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ActivityHelloWorld extends AppCompatActivity {
    private boolean isAnimationRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void onTextClick(View view) {
        final TextView movingText = findViewById(R.id.movingText);

        if (isAnimationRunning) {
            return; // Если анимация уже запущена, ничего не делаем
        }

        ObjectAnimator moveDown = ObjectAnimator.ofFloat(movingText, "translationY", getWindowManager().getDefaultDisplay().getHeight() - movingText.getHeight() - 250);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(movingText, "rotation", 180f);
        ObjectAnimator changeColor = ObjectAnimator.ofArgb(movingText, "textColor", Color.BLUE, Color.RED);

        ObjectAnimator moveUp = ObjectAnimator.ofFloat(movingText, "translationY", 0f);
        ObjectAnimator rotateBack = ObjectAnimator.ofFloat(movingText, "rotation", 0f);
        ObjectAnimator changeColorBack = ObjectAnimator.ofArgb(movingText, "textColor", Color.RED, Color.BLUE);

        final AnimatorSet set = new AnimatorSet();
        set.play(moveDown).with(rotate).with(changeColor);
        set.setDuration(3000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());

        final AnimatorSet reverseSet = new AnimatorSet();
        reverseSet.play(moveUp).with(rotateBack).with(changeColorBack);
        reverseSet.setDuration(3000);
        reverseSet.setInterpolator(new AccelerateDecelerateInterpolator());

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimationRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimationRunning = false;
                reverseSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimationRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        set.start();
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
