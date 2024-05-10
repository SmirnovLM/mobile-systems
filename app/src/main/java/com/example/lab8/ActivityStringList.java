package com.example.lab8;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityStringList extends AppCompatActivity {

    private StringList stringList;
    private EditText editTextWord;
    private TextView textViewAllWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        stringList = new StringList();
        editTextWord = findViewById(R.id.editTextWord);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonRemove = findViewById(R.id.buttonRemove);
        textViewAllWords = findViewById(R.id.textViewAllWords);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = editTextWord.getText().toString();
                if (!word.isEmpty()) {
                    stringList.addString(word);
                    editTextWord.setText("");
                    updateAllWordsTextView();
                }
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringList.removeLastString();
                updateAllWordsTextView();
            }
        });
    }

    private void updateAllWordsTextView() {
        textViewAllWords.setText(stringList.getAllWordsAsString());
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