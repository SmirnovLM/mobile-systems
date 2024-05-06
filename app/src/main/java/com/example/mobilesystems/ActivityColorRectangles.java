package com.example.mobilesystems;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesystems.ColorRectangles.CustomListAdapter;
import com.example.mobilesystems.ColorRectangles.ListItemModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityColorRectangles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_rectangles);

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
}