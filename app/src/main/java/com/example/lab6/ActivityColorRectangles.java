package com.example.lab6;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab6.ColorRectangles.CustomListAdapter;
import com.example.lab6.ColorRectangles.ListItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityColorRectangles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_rectangles);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.listView);
        List<ListItemModel> listItems = generateListItems(); // Генерируем список элементов

        CustomListAdapter adapter = new CustomListAdapter(this, listItems);
        listView.setAdapter(adapter);
    }

    private List<ListItemModel> generateListItems() {
        List<ListItemModel> listItems = new ArrayList<>();
        // Создаем несколько элементов списка с разными цветами
        listItems.add(new ListItemModel("Red", Color.RED, Color.WHITE));
        listItems.add(new ListItemModel("Green", Color.GREEN, Color.BLACK));
        listItems.add(new ListItemModel("Blue", Color.BLUE, Color.WHITE));
        listItems.add(new ListItemModel("Yellow", Color.YELLOW, Color.BLACK));
        return listItems;
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