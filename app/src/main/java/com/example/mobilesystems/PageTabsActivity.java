package com.example.mobilesystems;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobilesystems.fragments.PageMenuFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class PageTabsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_adapter_tabs);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        pagerAdapter = new MyPagerAdapter(this);
        pagerAdapter.addFragment(new PageMenuFragment(pagerAdapter), "Menu");
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(pagerAdapter.getTitle(position))
        ).attach();
    }


    // Внутренний класс адаптера для ViewPager2
    public class MyPagerAdapter extends FragmentStateAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> titles = new ArrayList<>();

        public MyPagerAdapter(AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }


        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
            notifyDataSetChanged();
        }

        public String getTitle(int position) {
            return titles.get(position);
        }

        public int getPosition(String title) {
            for (int i = 0; i < titles.size(); i++) {
                if (titles.get(i).equals(title)) {
                    return i;
                }
            }
            return -1;
        }


        public void removeFragment(int position) {
            fragments.remove(position);
            titles.remove(position);
            notifyDataSetChanged();
        }
    }
}