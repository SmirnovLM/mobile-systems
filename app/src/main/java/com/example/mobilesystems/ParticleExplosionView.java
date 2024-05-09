package com.example.mobilesystems;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ParticleExplosionView extends View {
    private List<Particle> particles;
    private Paint paint;
    private Random random;
    private float fountainX;
    private float fountainY;

    public ParticleExplosionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        particles = new ArrayList<>();
        paint = new Paint();
        random = new Random();
        setBackgroundColor(Color.BLACK);
        paint.setColor(Color.WHITE);
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
            fountainX = event.getX();
            fountainY = event.getY();
            spawnParticles();
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void spawnParticles() {
        for (int i = 0; i < 20; i++) {
            float speedX = (random.nextFloat() - 0.5f) * 20; // Random horizontal speed
            float speedY = -random.nextFloat() * 50; // Random vertical speed (upward)
            float radius = random.nextFloat() * 10 + 5; // Random radius between 5 and 15
            particles.add(new Particle(fountainX, fountainY, radius, speedX, speedY));
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(updateParticlesRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(updateParticlesRunnable);
        super.onDetachedFromWindow();
    }

    private Runnable updateParticlesRunnable = new Runnable() {
        @Override
        public void run() {
            updateParticles();
            invalidate();
            postDelayed(this, 30); // Update every 30 milliseconds
        }
    };

    private void updateParticles() {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle particle = iterator.next();
            particle.x += particle.speedX;
            particle.y += particle.speedY;
            particle.speedY += 1; // Gravity
            if (particle.y > getHeight()) {
                iterator.remove(); // Remove particles when they fall below the view
            }
        }
    }

    private static class Particle {
        float x;
        float y;
        float radius;
        float speedX;
        float speedY;

        Particle(float x, float y, float radius, float speedX, float speedY) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.speedX = speedX;
            this.speedY = speedY;
        }
    }
}
