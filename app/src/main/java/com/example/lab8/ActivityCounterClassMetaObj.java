package com.example.lab8;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ActivityCounterClassMetaObj extends AppCompatActivity {
    private Class<?> counterClass;
    private Object counterObj;
    private Constructor<?> constructor;
    private Method incrementMethod, resetMethod, getValueMethod;

    private TextView textViewCounter;
    private Button buttonIncrement;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_class);
        textViewCounter = findViewById(R.id.textViewCounter);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        buttonReset = findViewById(R.id.buttonReset);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        try {
            counterClass = Class.forName("com.example.lab8.CounterClass");
            constructor = counterClass.getConstructor();
            counterObj = constructor.newInstance();

            incrementMethod = counterClass.getMethod("increment");
            resetMethod = counterClass.getMethod("reset");
            getValueMethod = counterClass.getMethod("getValue");

            textViewCounter.setText(String.valueOf(getValueMethod.invoke(counterObj)));

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    incrementMethod.invoke(counterObj);
                    textViewCounter.setText(String.valueOf(getValueMethod.invoke(counterObj)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resetMethod.invoke(counterObj);
                    textViewCounter.setText(String.valueOf(getValueMethod.invoke(counterObj)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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