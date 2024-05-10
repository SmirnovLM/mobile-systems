package com.example.lab9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ParticleFontanView extends View {
    private List<Particle> particles;
    private Paint paint;
    private Random random;

    public ParticleFontanView(Context context, AttributeSet attrs) {
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
        createParticles(); // Создаем частицы при каждой отрисовке
        moveParticles(); // Двигаем частицы
        drawParticles(canvas); // Отрисовываем частицы на холсте
        invalidate(); // Перерисовываем вид
    }

    private void createParticles() {
        // Задаем горлышко для создания частиц
        float startX = getWidth() / 2f - 20;
        float endX = getWidth() / 2f + 20;
        float startY = getHeight() - 20;

        // Создаем частицы в указанной области
        for (int i = 0; i < 5; i++) {
            float x = random.nextFloat() * (endX - startX) + startX;
            float y = startY;
            float speedX = random.nextFloat() * 14 - 7;
            float speedY = -random.nextFloat() * 50;
            float radius = random.nextFloat() * 10 + 5;
            particles.add(new Particle(x, y, radius, speedX, speedY));
        }
    }

    private void moveParticles() {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle particle = iterator.next();
            particle.x += particle.speedX;
            particle.y += particle.speedY;
            particle.speedY += 1; // Гравитация
            if (particle.y > getHeight()) {
                iterator.remove(); // Удаляем частицы, когда они выходят за пределы видимости
            }
        }
    }

    private void drawParticles(Canvas canvas) {
        for (Particle particle : particles) {
            canvas.drawCircle(particle.x, particle.y, particle.radius, paint);
        }
    }

    // Класс для представления частиц
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
