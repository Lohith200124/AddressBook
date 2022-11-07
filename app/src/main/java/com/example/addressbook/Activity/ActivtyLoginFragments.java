package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.addressbook.Adapter.FragmentLoginPageAdapter;
import com.example.addressbook.R;
import com.google.android.material.tabs.TabLayout;

/**
 * login fragment activity
 *
 */
public class ActivtyLoginFragments extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    /**
     * created fragment manger and tab layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_login_fragments);
        tabLayout = findViewById(R.id.tabLayout1);
        viewPager = findViewById(R.id.viewPager1);
        FragmentManager fm = getSupportFragmentManager();
        FragmentLoginPageAdapter fa = new FragmentLoginPageAdapter(fm);
        viewPager.setAdapter(fa);
        tabLayout.setupWithViewPager(viewPager);
       // Intent intent = new Intent(this, AddressBookActivityFragments.class);

    }
}