package com.example.lab7;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityPageStack extends AppCompatActivity {

    static private PageStackHandler pageStackHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_stack);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (pageStackHandler == null) {
            pageStackHandler = new PageStackHandler();
        }

        TextView stackAddTextView = findViewById(R.id.stackAddTextView);
        TextView stackDelTextView = findViewById(R.id.stackDelTextView);
        TextView stackPage = findViewById(R.id.stackDepthTextView);
        Button backButton = findViewById(R.id.backButton);
        Button forwardButton = findViewById(R.id.forwardButton);

        updateCounters(stackAddTextView, stackDelTextView, stackPage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                pageStackHandler.pageRemoved();
                updateCounters(stackAddTextView, stackDelTextView, stackPage);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ActivityPageStack.this, ActivityPageStack.class);
                //startActivity(intent);
                pageStackHandler.pageAdded();
                updateCounters(stackAddTextView, stackDelTextView, stackPage);
            }
        });
    }

    private void updateCounters(TextView stackAddTextView, TextView stackDelTextView, TextView stackPage) {
        stackPage.setText("Глубина стека: " + pageStackHandler.getStackPage());
        stackAddTextView.setText("Кол-во добавленных страниц: " + pageStackHandler.getAddedPagesCount());
        stackDelTextView.setText("Кол-во удаленных страниц: " + pageStackHandler.getRemovedPagesCount());
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