package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class IssueTab extends AppCompatActivity {
    TabLayout tab;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_tab);
        tab= findViewById(R.id.IssueTabs);
        pager= findViewById(R.id.IssuePager);

        PagerAdapter adapter= new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tab.setupWithViewPager(pager);
    }
}