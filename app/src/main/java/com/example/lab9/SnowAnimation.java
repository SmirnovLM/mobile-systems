package com.example.lab9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowAnimation extends View {
    private static final int NUM_SNOWFLAKES = 100;
    private final List<Snowflake> snowflakes = new ArrayList<>();
    private final Random random = new Random();
    private Paint paint;

    public SnowAnimation(Context context) {
        super(context);
        init();
    }

    public SnowAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SnowAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        // Создание снежинок и их исходное расположение за пределами экрана
        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            snowflakes.add(createSnowflake(getWidth()));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Snowflake snowflake : snowflakes) {
            canvas.drawCircle(snowflake.x, snowflake.y, snowflake.size, paint);
        }
        moveSnowflakes();
        invalidate();
    }

    private void moveSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.y += snowflake.speed;
            if (snowflake.y > getHeight()) {
                // Если снежинка вышла за пределы экрана, возвращаем её в начальное положение сверху
                snowflake.y = 0;
                snowflake.x = random.nextFloat() * getWidth(); // Устанавливаем новую случайную координату по горизонтали
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // При изменении размеров View обновляем положения снежинок
        for (Snowflake snowflake : snowflakes) {
            snowflake.x = random.nextFloat() * w;
            snowflake.y = random.nextFloat() * h;
        }
    }

    private Snowflake createSnowflake(int width) {
        float size = random.nextFloat() * 10; // Размер снежинки от 0 до 10
        float x = random.nextFloat() * width; // Случайная координата по ширине экрана
        float y = random.nextFloat() * getHeight() - getHeight(); // Начальная координата за пределами экрана
        float speed = 3 + random.nextFloat() * 2; // Скорость снежинки от 1 до 6
        return new Snowflake(x, y, size, speed);
    }

    private static class Snowflake {
        float x;
        float y;
        float size;
        float speed;

        Snowflake(float x, float y, float size, float speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
        }
    }
}
