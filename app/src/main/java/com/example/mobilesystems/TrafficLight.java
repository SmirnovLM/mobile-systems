package com.example.mobilesystems;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TrafficLight {

    private ImageView lightView;
    private int colorResource;
    private boolean isActive;

    public TrafficLight(ImageView lightView) {
        this.lightView = lightView;
        this.isActive = false;
        this.lightView.setAlpha(0.3f);
    }

    public void setColor(int colorResource) {
        this.colorResource = colorResource;
        this.lightView.setImageResource(colorResource);
    }

    public void setActive(boolean active) {
        this.isActive = active;
        // Устанавливаем полную прозрачность, если сигнал активен, и неполную в противном случае
        float alpha = active ? 1.0f : 0.3f;
        this.lightView.setAlpha(alpha);
    }

    public boolean isActive() {
        return isActive;
    }

    public int getColorResource() {
        return colorResource;
    }

}
