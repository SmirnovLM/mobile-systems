package com.example.lab6;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab6.ConfGroup.AppSettings;

import java.util.Objects;

public class ActivityConfigurationGroup extends AppCompatActivity {

    private EditText editTextConfGroupLogin, editTextConfGroupPassword;
    private CheckBox checkBoxConfGroup;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_group);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextConfGroupLogin = findViewById(R.id.editTextConfGroupLogin);
        editTextConfGroupPassword = findViewById(R.id.editTextConfGroupPassword);
        checkBoxConfGroup = findViewById(R.id.checkBoxCongGroup);
        loginButton = findViewById(R.id.buttonLogin);

        // Установка сохраненных значений, если они есть
        editTextConfGroupLogin.setText(AppSettings.getLogin(this));
        editTextConfGroupPassword.setText(AppSettings.getPassword(this));
        checkBoxConfGroup.setChecked(AppSettings.getChecked(this));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxConfGroup.isChecked()) {
                    AppSettings.saveLogin(ActivityConfigurationGroup.this, editTextConfGroupLogin.getText().toString());
                    AppSettings.savePassword(ActivityConfigurationGroup.this, editTextConfGroupPassword.getText().toString());
                }
                else {
                    AppSettings.saveLogin(ActivityConfigurationGroup.this, "");
                    AppSettings.savePassword(ActivityConfigurationGroup.this, "");
                }
                finish();
            }
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
            if (!checkBoxConfGroup.isChecked()) {
                AppSettings.saveLogin(ActivityConfigurationGroup.this, "");
                AppSettings.savePassword(ActivityConfigurationGroup.this, "");
            }

            // Завершаем текущую активность
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}