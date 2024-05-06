package com.example.mobilesystems;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesystems.ConfGroup.AppSettings;

public class ActivityConfigurationGroup extends AppCompatActivity {

    private EditText editTextConfGroup;
    private CheckBox checkBoxConfGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_group);

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
}