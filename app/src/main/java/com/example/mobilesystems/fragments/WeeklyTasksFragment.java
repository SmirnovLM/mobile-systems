package com.example.mobilesystems.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mobilesystems.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class WeeklyTasksFragment extends Fragment {

    private ListView listView;
    private List<Task> tasks;

    ArrayAdapter<Task> adapter;

    private void sortTasksByDate() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int yearComparison = Integer.compare(t1.getYear(), t2.getYear());
                if (yearComparison != 0) {
                    return yearComparison;
                }

                int monthComparison = Integer.compare(t1.getMonth(), t2.getMonth());
                if (monthComparison != 0) {
                    return monthComparison;
                }

                return Integer.compare(t1.getDay(), t2.getDay());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_tasks, container, false);
        listView = view.findViewById(R.id.list_menu_view);

        // Создание списка задач
        tasks = new ArrayList<>();
        tasks.add(new Task(20, 04, 2024, "Подготовить презентацию"));
        tasks.add(new Task(19, 04, 2024, "Провести собрание"));
        tasks.add(new Task(22, 04, 2024, "Закончить отчет"));
        tasks.add(new Task(20, 04, 2024, "Подготовить презентацию"));
        tasks.add(new Task(19, 04, 2024, "Провести собрание"));
        tasks.add(new Task(22, 04, 2024, "Закончить отчет"));
        tasks.add(new Task(20, 04, 2024, "Подготовить презентацию"));
        tasks.add(new Task(19, 04, 2024, "Провести собрание"));
        tasks.add(new Task(22, 04, 2024, "Закончить отчет"));
        tasks.add(new Task(20, 04, 2024, "Подготовить презентацию"));
        tasks.add(new Task(19, 04, 2024, "Провести собрание"));
        tasks.add(new Task(22, 04, 2024, "Закончить отчет"));

        // Сортировка списка по дате
        sortTasksByDate();

        // Создание адаптера для списка задач
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        Button addTaskButton = view.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> showDeleteTaskDialog(position));

        return view;
    }


    // Класс модели для задачи
    private static class Task {
        private int year, month, day;
        private String description;

        public Task(int day, int month, int year, String description) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.description = description;
        }

        public int getYear() {
            return year;
        }
        public int getMonth() {
            return month;
        }
        public int getDay() {
            return day;
        }
        @NonNull
        @Override
        public String toString() {
            return String.format("%02d-%02d-%04d: %s", day, month, year, description);
        }
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Добавить задачу");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        EditText dateEditText = dialogView.findViewById(R.id.edit_text_date);
        EditText descriptionEditText = dialogView.findViewById(R.id.edit_text_description);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Обновляем текст в EditText с датой
                                String dateString = String.format(Locale.getDefault(), "%02d-%02d-%04d", dayOfMonth, month + 1, year);
                                dateEditText.setText(dateString);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });

        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dateStr = dateEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (!dateStr.isEmpty() && !description.isEmpty()) {
                    String[] parts = dateStr.split("-");
                    if (parts.length == 3) {
                        int day = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int year = Integer.parseInt(parts[2]);

                        tasks.add(new Task(day, month, year, description));

                        sortTasksByDate();

                        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, tasks);
                        listView.setAdapter(adapter);
                    }
                }
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); }
        });
        builder.show();
    }

    private void showDeleteTaskDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Удалить задачу");
        builder.setMessage("Вы уверены, что хотите удалить эту задачу?");

        builder.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Удаление задачи из списка
                tasks.remove(position);
                // Обновление списка
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ничего не делаем, просто закрываем диалоговое окно
            }
        });

        builder.show();
    }
}
