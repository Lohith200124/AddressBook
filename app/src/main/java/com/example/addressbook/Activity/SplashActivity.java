package com.example.addressbook.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.addressbook.MainActivity;
import com.example.addressbook.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sharedPreferences.getBoolean("flag", false);
                Intent intent;
                if (check) {
                    intent = new Intent(SplashActivity.this, ToolBar.class);
                }
                else{
                    intent = new Intent(SplashActivity.this, ActivtyLoginFragments.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}