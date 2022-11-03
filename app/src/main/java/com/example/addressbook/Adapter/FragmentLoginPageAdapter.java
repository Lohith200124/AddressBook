package com.example.addressbook.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.addressbook.Fragments.LoginPage;
import com.example.addressbook.Fragments.SignupPage;

public class FragmentLoginPageAdapter extends FragmentPagerAdapter {


    public FragmentLoginPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new LoginPage();
        }
        else if(position == 1){
            return  new SignupPage();
        }
        else{
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

        if(position == 0){
            return "Login";
        }
        else
        {
            return "Signup";
        }
    }
}
