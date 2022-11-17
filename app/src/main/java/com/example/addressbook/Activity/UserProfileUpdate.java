package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.addressbook.R;

public class UserProfileUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);
        CoustomView coustomView = new CoustomView(this);
    }
}