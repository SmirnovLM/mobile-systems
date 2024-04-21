package com.example.mobilesystems;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import android.content.res.ColorStateList;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // *** Button color *** \\
        Button myButton = findViewById(R.id.button2);
        myButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void onClick(View v) {
                myButton.setSelected(!myButton.isSelected());
                if (myButton.isSelected()) {
                    myButton.setBackgroundTintList(getResources().getColorStateList(R.color.button_pressed));
                } else {
                    myButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.button_default)));
                    //myButton.setBackgroundTintList(getResources().getColorStateList(R.color.button_default));
                }
            }
        });



        // *** Button Text Status *** \\
        Button buttonTextStatus = findViewById(R.id.buttonTextPress);
        TextView statusTextView = findViewById(R.id.statusTextView);

        statusTextView.setText("Статус: Отпущена");

        buttonTextStatus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void onClick(View v) {
                if (buttonTextStatus.isSelected()) {
                    buttonTextStatus.setSelected(false);
                    buttonTextStatus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.button_default)));
                    statusTextView.setText("Статус: Отпущена");
                } else {
                    buttonTextStatus.setSelected(true);
                    buttonTextStatus.setBackgroundTintList(getResources().getColorStateList(R.color.button_pressed));
                    //myButton.setBackgroundTintList(getResources().getColorStateList(R.color.button_default));
                    statusTextView.setText("Статус: Нажата");
                }
            }
        });



        // *** SeekBar *** \\
        SeekBar seekBar = findViewById(R.id.seekBar);
        final TextView textView = findViewById(R.id.text_seek_value);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        // *** Clicker *** \\
        Button clickButton = findViewById(R.id.buttonClick);
        final TextView textClicker = findViewById(R.id.textClicker);
        final int[] clickCount = {0};
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount[0]++;
                textClicker.setText("Кнопка была нажата " + clickCount[0] + " раз");
            }
        });



        // Time and Date
        Button buttonDate = findViewById(R.id.button_date);
        Button buttonTime = findViewById(R.id.button_time);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем текущую дату
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Создаем диалог выбора даты
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                                Toast.makeText(MainActivity.this, "Выбранная дата: " + selectedDate, Toast.LENGTH_SHORT).show();
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем текущее время
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Создаем диалог выбора времени
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String selectedTime = hourOfDay + ":" + minute;
                                Toast.makeText(MainActivity.this, "Выбранное время: " + selectedTime, Toast.LENGTH_SHORT).show();
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });




        // *** Items *** \\
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();
                if (!selectedItem.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Выбрано: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ничего не делать, если не выбрано ничего
            }
        });



        // *** Switch *** \\
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchButton = findViewById(R.id.switchButton);
        TextView statusT = findViewById(R.id.status);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statusT.setText("Состояние: Включен");
                } else {
                    statusT.setText("Состояние: Выключен");
                }
            }
        });


        // *** New Page for inputting date *** \\
        Button buttonNewPage = findViewById(R.id.button_info);
        buttonNewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PageForInputData.class);
                startActivity(intent);
            }
        });

    }
}