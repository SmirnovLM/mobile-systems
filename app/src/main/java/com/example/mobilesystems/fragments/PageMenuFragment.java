package com.example.mobilesystems.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobilesystems.PageTabsActivity;
import com.example.mobilesystems.R;

public class PageMenuFragment extends Fragment {
    private final PageTabsActivity.MyPagerAdapter adapter;

    public PageMenuFragment(PageTabsActivity.MyPagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_menu, container, false);

        Button addTextButton = view.findViewById(R.id.add_text_button);
        Button addDateButton = view.findViewById(R.id.add_date_button);
        Button addTimeButton = view.findViewById(R.id.add_time_button);
        Button addTasksButton = view.findViewById(R.id.add_tasks_button);
        Button addWebButton = view.findViewById(R.id.add_web_button);
        Button addUpDwMenuButton = view.findViewById(R.id.add_menu_button);
        Button addLiMeButton = view.findViewById(R.id.add_list_menu_button);

        Button delTextButton = view.findViewById(R.id.delete_text_button);
        Button delDateButton = view.findViewById(R.id.delete_date_button);
        Button delTimeButton = view.findViewById(R.id.delete_time_button);
        Button delTasksButton = view.findViewById(R.id.delete_tasks_button);
        Button delWebButton = view.findViewById(R.id.del_web_button);
        Button delUpDwMenuButton = view.findViewById(R.id.del_menu_button);
        Button delLiMeButton = view.findViewById(R.id.del_list_menu_button);


        addTextButton.setOnClickListener((view1) -> {
            adapter.addFragment(new PageTextFragment(), "Text App");
            addTextButton.setEnabled(false);
            delTextButton.setEnabled(true);
        });

        addDateButton.setOnClickListener((view1) -> {
            adapter.addFragment(new PageDateFragment(), "Data App");
            addDateButton.setEnabled(false);
            delDateButton.setEnabled(true);
        });

        addTimeButton.setOnClickListener((view1) -> {
            adapter.addFragment(new PageTimeFragment(), "Time App");
            addTimeButton.setEnabled(false);
            delTimeButton.setEnabled(true);
        });

        addTasksButton.setOnClickListener((view1) -> {
            adapter.addFragment(new WeeklyTasksFragment(), "Tasks App");
            addTasksButton.setEnabled(false);
            delTasksButton.setEnabled(true);
        });

        addWebButton.setOnClickListener((view1) -> {
            adapter.addFragment(new WebViewFragment(), "Web App");
            addWebButton.setEnabled(false);
            delWebButton.setEnabled(true);
        });

        addUpDwMenuButton.setOnClickListener((view1) -> {
            adapter.addFragment(new PageUpDwFragment(), "U/D App");
            addUpDwMenuButton.setEnabled(false);
            delUpDwMenuButton.setEnabled(true);
        });

        addLiMeButton.setOnClickListener((view1) -> {
            adapter.addFragment(new PageLiMeFragment(), "LiMe App");
            addLiMeButton.setEnabled(false);
            delLiMeButton.setEnabled(true);
        });



        delTextButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("Text App"));
            addTextButton.setEnabled(true);
            delTextButton.setEnabled(false);

        });

        delDateButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("Data App"));
            addDateButton.setEnabled(true);
            delDateButton.setEnabled(false);
        });

        delTimeButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("Time App"));
            addTimeButton.setEnabled(true);
            delTimeButton.setEnabled(false);
        });

        delTasksButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("Tasks App"));
            addTasksButton.setEnabled(true);
            delTasksButton.setEnabled(false);
        });

        delWebButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("Web App"));
            addWebButton.setEnabled(true);
            delWebButton.setEnabled(false);
        });

        delUpDwMenuButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("U/D App"));
            addUpDwMenuButton.setEnabled(true);
            delUpDwMenuButton.setEnabled(false);
        });

        delLiMeButton.setOnClickListener((view1) -> {
            adapter.removeFragment(adapter.getPosition("LiMe App"));
            addLiMeButton.setEnabled(true);
            delLiMeButton.setEnabled(false);
        });

        return view;
    }
}