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
                SharedPrefernceClass sp = new SharedPrefernceClass(getApplicationContext());
                /*SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();*/
                Boolean check = sp.sharedPreferences.getBoolean("flag",false);
                Intent intent;
                intent = (check=true)? new Intent(SplashActivity.this, HomePageActivity.class):
                         new Intent(SplashActivity.this, LoginPageActivity.class);

              /*  if (check) {
                    intent =
                    //edit.putBoolean("flag",true);
                }
                else{

                }*/
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}