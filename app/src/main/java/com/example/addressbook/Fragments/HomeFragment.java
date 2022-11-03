package com.example.addressbook.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

RecyclerView recyclerView;
FloatingActionButton floatingActionButton;
ArrayList<UserInfo> listUserInfo;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DataBaseHelper db = DataBaseHelper.getDb(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listUserInfo =(ArrayList<UserInfo>) db.dao().getUserInfo();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),listUserInfo);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }
}