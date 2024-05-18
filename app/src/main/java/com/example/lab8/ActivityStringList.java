package com.example.lab8;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        textViewAllWords.setMovementMethod(LinkMovementMethod.getInstance());

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
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < stringList.getStringList().size(); i++) {
            String originalWord = stringList.getStringList().get(i);
            String word = originalWord;
            if (i == 0) {
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
            }

            SpannableString spannableString = new SpannableString(word);
            final int index = i;
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    showWordInfo(originalWord, index);
                }
            };

            spannableString.setSpan(clickableSpan, 0, word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.append(spannableString);
            if (i < stringList.getStringList().size() - 1) {
                spannableStringBuilder.append(", ");
            }
        }
        textViewAllWords.setText(spannableStringBuilder);
    }

    private void showWordInfo(String word, int index) {

        String message =
                "Порядковое место: " + (index + 1) + "\n" +
                "Длина: " + word.length() + "\n" +
                "Количество гласных: " + countVowels(word) + "\n" +
                "Количество согласных: " + countConsonants(word);

        new AlertDialog.Builder(this)
                .setTitle("Информация о слове")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> updateAllWordsTextView())
                .show();
    }

    private int countVowels(String word) {
        int count = 0;
        String vowels = "aeiouyаеёиоуыэюя";
        for (char c : word.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    private int countConsonants(String word) {
        int count = 0;
        String consonants = "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ";
        for (char c : word.toLowerCase().toCharArray()) {
            if (consonants.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
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