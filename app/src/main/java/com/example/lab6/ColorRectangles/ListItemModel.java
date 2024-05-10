package com.example.lab6.ColorRectangles;

public class ListItemModel {
    private String colorName;
    private int backgroundColor;
    private int textColor;

    public ListItemModel(String colorName, int backgroundColor, int textColor) {
        this.colorName = colorName;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getColorName() {
        return colorName;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }
}
