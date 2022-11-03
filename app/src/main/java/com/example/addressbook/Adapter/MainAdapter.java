package com.example.addressbook.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.addressbook.Fragments.CreateFragment;
import com.example.addressbook.Fragments.HomeFragment;

public class MainAdapter extends FragmentPagerAdapter {
    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new CreateFragment();
        }else if(position == 0){
            return new HomeFragment();
        }
        else
        {
            return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Home";
        } else if (position == 1)
        {
            return "Create";
        } else{
            return "";
        }
        }
}
