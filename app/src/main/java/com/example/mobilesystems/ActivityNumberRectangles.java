package com.example.mobilesystems;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivityNumberRectangles extends AppCompatActivity {

    private List<String> rectangleList;
    private ArrayAdapter<String> adapter;
    private int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_rectangles);

        Button addButton = findViewById(R.id.addButton);
        ListView rectangleListView = findViewById(R.id.rectangleListView);

        rectangleList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rectangleList);
        rectangleListView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRectangle();
            }
        });

        rectangleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeRectangle(position);
            }
        });
    }

    private void addRectangle() {
        rectangleList.add("Элемент " + counter++);
        adapter.notifyDataSetChanged();
    }

    private void removeRectangle(int position) {
        rectangleList.remove(position);
        adapter.notifyDataSetChanged();
    }
}