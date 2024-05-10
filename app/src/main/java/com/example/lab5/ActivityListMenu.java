package com.example.lab5;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab5.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ActivityListMenu extends AppCompatActivity {

    private ListView listView;
    private List<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        listView = findViewById(R.id.list_menu_view);

        itemList = new ArrayList<>(Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        // Регистрация списка для контекстного меню
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // Получаем информацию о пункте меню
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // Получаем индекс выбранного элемента списка
        int selectedIndex = info.position;

        // Получаем название выбранного пункта меню
        String menuItemTitle = item.getTitle().toString();

        // Показываем название выбранного пункта в виде всплывающего сообщения
        Toast.makeText(this, "Selected Menu Item: " + menuItemTitle, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Selected Index: " + selectedIndex, Toast.LENGTH_SHORT).show();

        return true;
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