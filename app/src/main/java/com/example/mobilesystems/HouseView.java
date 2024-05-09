package com.example.mobilesystems;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class HouseView extends View {
    private Paint paint;
    private Path roofPath;

    public HouseView(Context context) {
        super(context);
        init();
    }

    public HouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HouseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        roofPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();

        // Рисуем небо
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, width, height * 2 / 3, paint);

        // Рисуем землю
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, height * 2 / 3, width, height, paint);

        // Рисуем стены дома
        paint.setColor(Color.rgb(204, 119, 34)); // Охра
        float houseHeight = height / 4;
        canvas.drawRect(width / 4, height / 2, width * 3 / 4, height * 5 / 6, paint);

        // Рисуем трубу
        paint.setColor(Color.RED);
        canvas.drawRect(width * 12 / 20, height / 4, width * 14 / 20, height / 2, paint);

        // Рисуем крышу
        paint.setColor(Color.rgb(139, 69, 19)); // Коричневый
        roofPath.reset();
        roofPath.moveTo(width / 6, height / 2);
        roofPath.lineTo(width / 2, height / 3);
        roofPath.lineTo(width * 5 / 6, height / 2);
        roofPath.lineTo(width / 6, height / 2);
        roofPath.close();
        canvas.drawPath(roofPath, paint);

    }
}
