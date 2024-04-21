package com.example.mobilesystems;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PageForInputData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page_for_input_data);

        EditText editTextLastName = findViewById(R.id.lastNameEditText);
        EditText editTextFirstName = findViewById(R.id.firstNameEditText);
        EditText editTextPatronymic = findViewById(R.id.patronymicEditText);
        EditText editTextAge = findViewById(R.id.ageEditText);

        EditText editTextBirthDate = findViewById(R.id.birthDateEditText);
        editTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем текущую дату
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Создаем DatePickerDialog и устанавливаем слушатель для выбора даты
                DatePickerDialog datePickerDialog = new DatePickerDialog(PageForInputData.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                                // Формируем строку с выбранной датой и устанавливаем её в поле ввода
                                String dateString = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                                editTextBirthDate.setText(dateString);
                            }
                        }, year, month, dayOfMonth);

                // Показываем DatePickerDialog
                datePickerDialog.show();
            }
        });


        Button buttonSubmit = findViewById(R.id.submitButton);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner spinnerGender = findViewById(R.id.genderSpinner);
                String gender = spinnerGender.getSelectedItem().toString();

                if (gender.isEmpty()) {

                    Toast.makeText(PageForInputData.this, "Выберите пол", Toast.LENGTH_SHORT).show();
                    return;
                }

                String message = "Фамилия: " + editTextLastName.getText().toString() + "\n" +
                        "Имя: " + editTextFirstName.getText().toString() + "\n" +
                        "Отчество: " + editTextPatronymic.getText().toString() + "\n" +
                        "Возраст: " + editTextAge.getText().toString() + "\n" +
                        "Дата рождения: " + editTextBirthDate.getText().toString() + "\n" +
                        "Пол: " + gender;
                Log.i("Info", message);

                finish();

            }
        });
    }
}