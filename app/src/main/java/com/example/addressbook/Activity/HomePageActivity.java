package com.example.addressbook.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.Fragments.HomeFragment;
import com.example.addressbook.R;

/**
 * it is a home page having tool bar and (recylerview floating action button) - in home fragment
 */
public class HomePageActivity extends AppCompatActivity {
    private  Toolbar toolBar;
String fName;
    private  Button create;
   // Menu menu1;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_too_bar);
        toolBar = (Toolbar) findViewById(R.id.ToolBar);
        /*viewPager = findViewById(R.id.viewPagerMain);
        tabLayout = findViewById(R.id.tabLayoutMain);*/
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("ADDRESS BOOK");
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.MainFrame,new HomeFragment(),fName);
        ft.commit();
        /* MainAdapter adapter = new MainAdapter(fm);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_options,menu);
        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    /*    MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });*/
   /*   searchItem =    menu1.findItem(R.id.search_button);*/
        return super.onCreateOptionsMenu(menu);
    }

//public void setDataAccordingToYourNeed(){
//        HomeFragment homeFragment = (HomeFragment) getFragmentManager().findFragmentByTag(fName);
//
//}
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int itemId= item.getItemId();
        if(itemId == R.id.SignOut){
           SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferences.edit();
           editor.putBoolean("flag",false).apply();
           Intent intent = new Intent(HomePageActivity.this,ActivtyLoginFragments.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           startActivity(intent);

       }else if(itemId == R.id.search){
            SearchManager searchManager = (SearchManager) HomePageActivity.this.getSystemService(Context.SEARCH_SERVICE);

            SearchView searchView = null;
            if (item!= null) {
                searchView = (SearchView) item.getActionView();
            }
            if (searchView != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(HomePageActivity.this.getComponentName()));
            }
            //SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);;
            try {
                searchView =  (SearchView) MenuItemCompat.getActionView(item);
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
try{
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            recyclerViewAdapter.getFilter().filter(newText);
            return false;
        } });//
}catch(NullPointerException e){
    e.printStackTrace();
}
}  return super.onOptionsItemSelected(item);
    }}
