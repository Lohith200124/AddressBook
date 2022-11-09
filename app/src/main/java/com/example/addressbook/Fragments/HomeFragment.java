package com.example.addressbook.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.addressbook.Activity.AddressBookStructureActivity;
import com.example.addressbook.Activity.HomePageActivity;
import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * used to create the recyclerview an dfloating acton button
 */
public class HomeFragment extends Fragment {

RecyclerView recyclerView;
FloatingActionButton floatingActionButton;
List<UserInfo> listUserInfo = new ArrayList<>();
//Intent intent = new Intent(getActivity(), HomePageActivity.class);
RecyclerViewAdapter recyclerViewAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DataBaseHelper db = DataBaseHelper.getDb(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        floatingActionButton = view.findViewById(R.id.FloatingActionButton1);
        recyclerView = view.findViewById(R.id.rView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listUserInfo = (ArrayList<UserInfo>)db.userInfoDao().getUserInfo();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddressBookStructureActivity.class);
                startActivity(intent);

            }
        });
         recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),listUserInfo);
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
        return view;
    }


}