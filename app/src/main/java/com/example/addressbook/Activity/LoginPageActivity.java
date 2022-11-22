package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.zip.Inflater;

/**
 * login fragment activity
 *
 */
public class LoginPageActivity extends AppCompatActivity {
 /* private  TabLayout tabLayout;
  private  ViewPager viewPager;*/
   private TextView userName,password,ForgotPassword;
   private Button signin,SignUp;
   Toolbar toolbar;
   TextInputLayout textInputLayout_userName,textInputLayout_password;

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
        toolbar = (Toolbar) findViewById(R.id.SignUpPageToolBarLayout);
        userName = findViewById(R.id.emailAddress);
        password =findViewById(R.id.password);
        signin = findViewById(R.id.signIn);
        SignUp = findViewById(R.id.signUpPage);
        textInputLayout_password=findViewById(R.id.textInputLayoutForPasswordInLoginPage);
        textInputLayout_userName = findViewById(R.id.textInputLayoutForEmailAddressInLoginPage);
        // reset = view.findViewById(R.id.reset);
        ForgotPassword = findViewById(R.id.forgotPassword);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getDb(this);
        SharedPrefernceClass sp = new SharedPrefernceClass(getApplicationContext());
//        SharedPrefernceClass sp = new SharedPrefernceClass(SplashActivity.this);
       /* SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();*/
        Intent intent;
        intent = new Intent(this, HomePageActivity.class);
        signin.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          int counter=0;
                                          String UserName, Password;
                                          UserName = userName.getText().toString();
                                          Password = password.getText().toString();
                                          List<SignUp> list = dataBaseHelper.dao().getAllUSers();
                                          for (int i = 0; i < list.size(); i++) {
                                              if (UserName.equals(list.get(i).getUserName())) {
                                                  if (Password.equals(list.get(i).getPassword())) {
                                                     sp.getEdit().putBoolean("flag", true).apply();
                                                      startActivity(intent);
                                                      break;
                                                  } else {
                                                     // Toast.makeText(LoginPageActivity.this, "error", Toast.LENGTH_SHORT).show();
                                                      textInputLayout_password.setError("");
                                                      textInputLayout_password.setBoxBackgroundColor(Color.RED);
                                                      sp.getEdit().putBoolean("flag", false).apply();
                                                      //break;
                                                  }
                                             counter+=1; }
                                              if(counter>= list.size()-1){
                                                  textInputLayout_userName.setError("");
                                                  textInputLayout_userName.setBoxBackgroundColor(Color.RED);
                                                      userName.setText("");
                                                      password.setText("");
                                                      sp.getEdit().putBoolean("flag", false).apply();
                                              }
                                          }

                                      }
                                  }


        );
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, com.example.addressbook.Activity.ForgotPassword.class);
                startActivity(intent);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPageActivity.this,SignUpPageActivity.class));
            }
        });

        return ;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }
}
