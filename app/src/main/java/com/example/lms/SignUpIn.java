package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SignUpIn extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private VPAdapterSign adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_in);
    }
}