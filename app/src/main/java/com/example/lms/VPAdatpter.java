package com.example.lms;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdatpter extends FragmentStateAdapter {
    public VPAdatpter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new fragment1();
            case 1:
                return new fragment2();
            default:
                return new fragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
