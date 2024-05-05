package com.example.mobilesystems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PageStack extends AppCompatActivity {

    static private int stackDepth = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_stack);

        TextView stackDepthTextView = findViewById(R.id.stackDepthTextView);
        Button backButton = findViewById(R.id.backButton);
        Button forwardButton = findViewById(R.id.forwardButton);

        stackDepthTextView.setText("Глубина стека: " + stackDepth);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stackDepth > 0) {
                    stackDepth--;
                    finish();
                }
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackDepth++;
                Intent intent = new Intent(PageStack.this, PageStack.class);
                startActivity(intent);
            }
        });
    }
}