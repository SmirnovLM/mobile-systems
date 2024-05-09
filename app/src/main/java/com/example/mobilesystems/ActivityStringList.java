package com.example.mobilesystems;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityStringList extends AppCompatActivity {

    private StringList stringList;
    private EditText editTextWord;
    private TextView textViewAllWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_list);

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
}