package com.example.lab6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityConfigurationValue extends AppCompatActivity {
    private EditText editTextSettings;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_value);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextSettings = findViewById(R.id.editTextSettings);
        checkBox = findViewById(R.id.checkBoxSettings);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        // Загрузка сохраненных значений, если они есть
        editTextSettings.setText(sharedPreferences.getString("text", ""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked", false));

        // Слушатель изменений для поля с флажком
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Сохранение состояния флажка
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("checked", isChecked);
            editor.apply();
        });

        // Слушатель изменений для текстового поля
        editTextSettings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Метод вызывается до изменения текста
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается при изменении текста
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Метод вызывается после того, как изменения текста были применены
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("text", s.toString());
                editor.apply();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Сохранение текста из текстового поля при уходе из активности
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text", editTextSettings.getText().toString());
        editor.putBoolean("checked", checkBox.isChecked());
        editor.apply();
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