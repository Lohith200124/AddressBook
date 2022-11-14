package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbook.Adapter.FragmentLoginPageAdapter;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * login fragment activity
 *
 */
public class ActivtyLoginFragments extends AppCompatActivity {
 /* private  TabLayout tabLayout;
  private  ViewPager viewPager;*/
   private TextView userName,password,ForgotPassword;
   private Button signin,SignUp;

    /**
     * created fragment manger and tab layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_login_fragments);
       /* tabLayout = findViewById(R.id.tabLayout1);
        viewPager = findViewById(R.id.viewPager1);
        FragmentManager fm = getSupportFragmentManager();
        FragmentLoginPageAdapter fa = new FragmentLoginPageAdapter(fm);
        viewPager.setAdapter(fa);
        tabLayout.setupWithViewPager(viewPager);*/
       // Intent intent = new Intent(this, AddressBookActivityFragments.class);
        userName = findViewById(R.id.emailAddress);
        password =findViewById(R.id.password);
        signin = findViewById(R.id.signIn);
        SignUp = findViewById(R.id.signUpPage);
        // reset = view.findViewById(R.id.reset);
        ForgotPassword = findViewById(R.id.forgotPassword);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getDb(this);
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("flag", true).apply();
        Intent intent;
        intent = new Intent(this, HomePageActivity.class);
        signin.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          String UserName, Password;
                                          UserName = userName.getText().toString();
                                          Password = password.getText().toString();
                                          List<SignUp> list = dataBaseHelper.dao().getAllUSers();
                                          for (int i = 0; i < list.size(); i++) {
                                              if (UserName.equals(list.get(i).getUserName())) {
                                                  if (Password.equals(list.get(i).getPassword())) {
                                                      startActivity(intent);
                                                      break;
                                                  } else {
                                                      Toast.makeText(ActivtyLoginFragments.this, "error", Toast.LENGTH_SHORT).show();
                                                  }
                                              } else {
                                                  Toast.makeText(ActivtyLoginFragments.this, "invalid", Toast.LENGTH_SHORT).show();
                                                  userName.setText("");
                                                  password.setText("");
                                              }

                                          }

                                      }
                                  }


        );
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivtyLoginFragments.this, com.example.addressbook.Activity.ForgotPassword.class);
                startActivity(intent);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivtyLoginFragments.this,SignUpPageActivity.class));
            }
        });

        return ;

    }

    }
