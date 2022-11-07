package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.addressbook.R;

/**
 * it shows a splash screen and takes us into the homepageactivity
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sharedPreferences.getBoolean("flag",false);
                Intent intent;
                if (check) {
                    intent = new Intent(SplashActivity.this, HomePageActivity.class);
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