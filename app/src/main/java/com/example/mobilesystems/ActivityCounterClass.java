package com.example.mobilesystems;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityCounterClass extends AppCompatActivity {
    private CounterClass counter;
    private TextView textViewCounter;
    private Button buttonIncrement;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_class);

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
}