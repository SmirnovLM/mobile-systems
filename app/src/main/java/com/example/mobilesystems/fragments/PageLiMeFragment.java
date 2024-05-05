package com.example.mobilesystems.fragments;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobilesystems.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageLiMeFragment extends Fragment {

    private ListView listView;
    private List<String> itemList;
    private ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_menu, container, false);

        listView = view.findViewById(R.id.list_menu_view);

        itemList = new ArrayList<>(Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"));
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        // Регистрация списка для контекстного меню
        registerForContextMenu(listView);

        return view;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Получаем информацию о пункте меню
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // Получаем индекс выбранного элемента списка
        int selectedIndex = info.position;

        // Получаем название выбранного пункта меню
        String menuItemTitle = item.getTitle().toString();


        // Показываем название выбранного пункта в виде всплывающего сообщения
        Toast.makeText(requireContext(), "Selected Menu Item: " + menuItemTitle, Toast.LENGTH_SHORT).show();
        Toast.makeText(requireContext(), "Selected Index: " + selectedIndex, Toast.LENGTH_SHORT).show();

        return true;
    }
}
