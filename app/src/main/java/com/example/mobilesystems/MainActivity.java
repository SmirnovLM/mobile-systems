package com.example.mobilesystems;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        Button incrementButton = findViewById(R.id.incrementButton);
        Button decrementButton = findViewById(R.id.decrementButton);
        Button resetButton = findViewById(R.id.resetButton);

        // Обработчик клика на кнопку "Прибавить единицу"
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                updateCounter();
            }
        });

        // Обработчик клика на кнопку "Отнять единицу"
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                updateCounter();
            }
        });

        // Обработчик клика на кнопку "Обнулить"
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                updateCounter();
            }
        });
    }

    // Метод для обновления текста счетчика
    private void updateCounter() {
        counterTextView.setText(String.valueOf(counter));
    }

}