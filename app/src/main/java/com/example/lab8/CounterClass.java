package com.example.lab8;

public class CounterClass {
    private int value;

    public CounterClass() {
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
    }

    public void reset() {
        value = 0;
    }
}
