package com.example.addressbook.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.addressbook.Activity.ForgotPassword;
import com.example.addressbook.Activity.HomePageActivity;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;

import java.util.List;

/**
 * login page having login button and forgot password this is a fragment
 */

public class LoginPage extends Fragment {

    TextView userName,password,ForgotPassword;
    Button signin,reset;

    public LoginPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
       userName = view.findViewById(R.id.emailAddress);
       password = view.findViewById(R.id.password);
       signin = view.findViewById(R.id.signIn);
      // reset = view.findViewById(R.id.reset);
        ForgotPassword = view.findViewById(R.id.forgotPassword);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getDb(getContext());
        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("flag", true).apply();
        Intent intent;
        intent = new Intent(getActivity(), HomePageActivity.class);
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
                           Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(getContext(), "invalid", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getActivity(), com.example.addressbook.Activity.ForgotPassword.class);
                startActivity(intent);
            }
        });

        return view;

    }
}