package com.example.mobilesystems;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_of_numbers);
        //Animations();
        SumOfNumbers();
    }

    protected void Animations() {
        setContentView(R.layout.activity_animation_squares);
        View view = findViewById(R.id.s1);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_translate);

                view.startAnimation(anim);
            }
        });
    }

    protected void SumOfNumbers() {
        Button confirmButton = findViewById(R.id.openDialogButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForSum dialog = new DialogForSum();
                dialog.show(getSupportFragmentManager(), "custom dialog");
            }
        });

    }

    public void processNumbers(String firstNumber, String secondNumber) {
        int sum = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber);
        Toast.makeText(this, "Sum: " + sum, Toast.LENGTH_SHORT).show();
    }
}