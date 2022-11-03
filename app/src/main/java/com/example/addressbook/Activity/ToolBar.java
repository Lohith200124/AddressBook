package com.example.addressbook.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.addressbook.Adapter.MainAdapter;
import com.example.addressbook.R;
import com.google.android.material.tabs.TabLayout;

public class ToolBar extends AppCompatActivity {
    Toolbar toolBar;
    Button create;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_too_bar);
        toolBar = (Toolbar) findViewById(R.id.ToolBar);
        viewPager = findViewById(R.id.viewPagerMain);
        tabLayout = findViewById(R.id.tabLayoutMain);
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("ADDRESS BOOK");
        }
        FragmentManager fm = getSupportFragmentManager();
        MainAdapter adapter = new MainAdapter(fm);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_options,menu);
        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int itemId= item.getItemId();
       if(itemId == R.id.AccountInfo )
       {


       }else if(itemId == R.id.SignOut){
           SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferences.edit();
           editor.putBoolean("login",false);
           Intent intent = new Intent(ToolBar.this,ActivtyLoginFragments.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           startActivity(intent);

       }
        return super.onOptionsItemSelected(item);
    }
}