package com.example.addressbook.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbook.Activity.ActivtyLoginFragments;
import com.example.addressbook.Activity.CheckFor;
import com.example.addressbook.Activity.ForgotPassword;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;

import java.util.List;

/**
 * signup page fragment
 */
public class SignupPage extends Fragment {

    TextView firstName,lastName,userName, password, reEnterPassword, hero,ForgotPassword;
    Button create, reset;
    CheckFor checkFor =new CheckFor();
    int createCount=0;
    public SignupPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
/*
        View view = inflater.inflate(R.layout.fragment_signup_page, container, false);
        userName = view.findViewById(R.id.UserName);
        password = view.findViewById(R.id.NewPassword);
        reEnterPassword = view.findViewById(R.id.ReEnterPassword);
        create = view.findViewById(R.id.Create);
        hero = view.findViewById(R.id.HeroName);
        reset = view.findViewById(R.id.Reset);
        firstName = view.findViewById(R.id.FirstName);
        lastName = view.findViewById(R.id.LastName);
        DataBaseHelper databaseHelper = DataBaseHelper.getDb(getContext());
if(createCount<=0){
    createCount+=1;
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName, Password, ReEnterPassword, HeroName;
                UserName = userName.getText().toString();
                Password = password.getText().toString();
                ReEnterPassword = reEnterPassword.getText().toString();
                HeroName = hero.getText().toString();
                List<SignUp> list = databaseHelper.dao().getAllUSers();
                if (checkFor.validateEmail(UserName)) {
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            if (UserName.equals(list.get(i).getUserName())) {
                                userName.setText("");
                                break;
                            }
                        }
                    }
                    if (Password.equals(ReEnterPassword)) {
                        boolean check;
                        check = passwordValid(Password);
                        if (check) {
                            databaseHelper.dao().insert(new SignUp(UserName, Password, HeroName,
                                    firstName.getText().toString(), lastName.getText().toString()));
                            Intent intent = new Intent(getActivity(), ActivtyLoginFragments.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "follow convention", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            reEnterPassword.setText("");
                        }
                    }
                }
                else{
                    Toast.makeText(getActivity(), "error in email/userName format", Toast.LENGTH_SHORT).show();
                }
            }
        });}



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText("");
                password.setText("");
                reEnterPassword.setText("");
                hero.setText("");
                firstName.setText("");
                lastName.setText("");
                createCount=0;
            }
        });
*/

        return inflater.inflate(R.layout.fragment_signup_page, container, false);
    }
   /* public boolean passwordValid(String str){
        int upper = 0, lower = 0, number = 0, special = 0;
      if(str.length() >= 8)
        {
        for(int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z')
                upper++;
            else if (ch >= 'a' && ch <= 'z')
                lower++;
            else if (ch >= '0' && ch <= '9')
                number++;
            else
                special++;
        }
        if(upper>=1&lower>=1&number>=1&special>=1){
            return true;
        }
        else{
            return false;
        }
        }
      else{
          return  false;
      }
    }*/
}
