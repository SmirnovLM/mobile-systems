package com.example.lab9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleView extends View {

    private List<Particle> particles;
    private Paint paint;
    private Random random;

    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        particles = new ArrayList<>();
        paint = new Paint();
        random = new Random();
        setBackgroundColor(Color.BLACK); // Устанавливаем черный фон
        paint.setColor(Color.WHITE); // Устанавливаем белый цвет для кружков
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Particle particle : particles) {
            canvas.drawCircle(particle.x, particle.y, particle.radius, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            spawnParticles(x, y);
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void spawnParticles(float x, float y) {
        for (int i = 0; i < 10; i++) {
            float radius = random.nextFloat() * 10 + 5; // Random radius between 5 and 15
            particles.add(new Particle(x, y, radius));
        }
    }

    private static class Particle {
        float x;
        float y;
        float radius;

        Particle(float x, float y, float radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }
}
