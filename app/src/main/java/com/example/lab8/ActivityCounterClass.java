package com.example.lab8;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityCounterClass extends AppCompatActivity {
    private CounterClass counter;
    private TextView textViewCounter;
    private Button buttonIncrement;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_class);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        counter = new CounterClass();

        textViewCounter = findViewById(R.id.textViewCounter);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        buttonReset = findViewById(R.id.buttonReset);

        textViewCounter.setText(String.valueOf(counter.getValue()));

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.increment();
                textViewCounter.setText(String.valueOf(counter.getValue()));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.reset();
                textViewCounter.setText(String.valueOf(counter.getValue()));
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