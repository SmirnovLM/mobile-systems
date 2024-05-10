package com.example.lab6;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab6.ConfGroup.AppSettings;

import java.util.Objects;

public class ActivityConfigurationGroup extends AppCompatActivity {

    private EditText editTextConfGroup;
    private CheckBox checkBoxConfGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_group);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextConfGroup = findViewById(R.id.editTextConfGroup);
        checkBoxConfGroup = findViewById(R.id.checkBoxCongGroup);

        // Установка сохраненных значений, если они есть
        editTextConfGroup.setText(AppSettings.getText(this));
        checkBoxConfGroup.setChecked(AppSettings.getChecked(this));

        // Слушатель изменений в текстовом поле
        editTextConfGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Сохранение текста
                AppSettings.saveText(ActivityConfigurationGroup.this, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Слушатель изменений в поле с флажком
        checkBoxConfGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Сохранение состояния флажка
                AppSettings.saveChecked(ActivityConfigurationGroup.this, isChecked);
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