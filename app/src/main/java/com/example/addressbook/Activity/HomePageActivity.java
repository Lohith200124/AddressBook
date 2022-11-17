package com.example.addressbook.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;
import com.example.addressbook.Fragments.HomeFragment;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * it is a home page having tool bar and (recylerview floating action button) - in home fragment
 */
public class HomePageActivity extends AppCompatActivity {
    private  Toolbar toolBar;
  private  String fName;
    private  Button create;
  private  RecyclerView recyclerView;
  private  FloatingActionButton floatingActionButton;
   private List<UserInfo> listUserInfo = new ArrayList<>();
    //Intent intent = new Intent(getActivity(), HomePageActivity.class);
 private   RecyclerViewAdapter recyclerViewAdapter;
   // Menu menu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_too_bar);
        toolBar = (Toolbar) findViewById(R.id.ToolBar);
        /*viewPager = findViewById(R.id.viewPagerMain);
        tabLayout = findViewById(R.id.tabLayoutMain);*/
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("ADDRESS BOOK");
        }
        DataBaseHelper db = DataBaseHelper.getDb(this);
        floatingActionButton = findViewById(R.id.FloatingActionButton1);
        recyclerView = findViewById(R.id.rView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listUserInfo = (ArrayList<UserInfo>)db.userInfoDao().getUserInfo();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, AddressBookStructureActivity.class);
                startActivity(intent);

            }
        });
        //Comparator<UserName> cm1=Comparator.comparing(UserName::getFirstName);

        Collections.sort(listUserInfo, new Comparator<UserInfo>() {
            @Override
            public int compare(UserInfo userInfo, UserInfo t1) {
                return   (userInfo.getUserName().getFirstName()).compareToIgnoreCase(t1.getUserName().getFirstName());
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(HomePageActivity.this,listUserInfo);
       /* SearchView searchView = new SearchView(getActivity());
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
         /*intent.putExtra("RecyclerViewAdapter", (Serializable) recyclerViewAdapter);
        startActivity(intent);*/
        recyclerView.setAdapter(recyclerViewAdapter);

       /* FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();*/
        //ft.add(R.id.MainFrame,new HomeFragment(),fName);
       // ft.commit();
        /* MainAdapter adapter = new MainAdapter(fm);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_options,menu);
        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(2).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.getItem(3).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.getItem(4).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.getItem(5).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.getItem(6).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.getItem(7).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
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
        });*///menu.getItem(8).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
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
       }
        else if(itemId == R.id.search){
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
            return false;}});
        }
        catch(NullPointerException e)
        {
          e.printStackTrace();
        }}
        else  if(itemId == R.id.sortByName)
        {
            Collections.sort(listUserInfo, new Comparator<UserInfo>() {
                @Override
                public int compare(UserInfo userInfo, UserInfo t1) {
                    return   (userInfo.getUserName().getFirstName()).compareToIgnoreCase(t1.getUserName().getFirstName());
                }
            });
        }else if(itemId == R.id.sortByNameDes) {
            Collections.sort(listUserInfo, Collections.reverseOrder(new Comparator<UserInfo>() {
                        @Override
                        public int compare(UserInfo userInfo, UserInfo t1) {
                            return   (userInfo.getUserName().getFirstName()).compareToIgnoreCase(t1.getUserName().getFirstName());
                        }
                    }));
            recyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(this, "desc", Toast.LENGTH_SHORT).show();
        }else if(itemId == R.id.sortByPhone){
            Collections.sort(listUserInfo, new Comparator<UserInfo>() {
                @Override
                public int compare(UserInfo userInfo, UserInfo t1) {
                    return  (userInfo.getPhoneList().get(0).getPhonNo()).compareTo(t1.getPhoneList().get(0).getPhonNo());
                }

            });
        }else if(itemId == R.id.sortByphoneReverse){
            Collections.sort(listUserInfo,Collections.reverseOrder( new Comparator<UserInfo>() {
                @Override
                public int compare(UserInfo userInfo, UserInfo t1) {
                    return  (userInfo.getPhoneList().get(0).getPhonNo()).compareTo(t1.getPhoneList().get(0).getPhonNo());
                }

            }));
        }else if(itemId == R.id.sortByEmailDes){
            Collections.sort(listUserInfo,Collections.reverseOrder( new Comparator<UserInfo>() {
                @Override
                public int compare(UserInfo userInfo, UserInfo t1) {
                    return  (userInfo.getEmailList().get(0).getEmail()).compareTo(t1.getEmailList().get(0).getEmail());
                }

            }));
        }else if(itemId == R.id.sortByEmail){
            Collections.sort(listUserInfo, new Comparator<UserInfo>() {
                @Override
                public int compare(UserInfo userInfo, UserInfo t1) {
                    return  (userInfo.getEmailList().get(0).getEmail()).compareTo(t1.getEmailList().get(0).getEmail());
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }}
