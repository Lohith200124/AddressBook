package com.example.addressbook.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.addressbook.Activity.ActivtyLoginFragments;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;

import java.util.List;


public class SignupPage extends Fragment {

    TextView userName, password, reEnterPassword, hero;
    Button create, reset;

    public SignupPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_page, container, false);
        userName = view.findViewById(R.id.UserName);
        password = view.findViewById(R.id.NewPassword);
        reEnterPassword = view.findViewById(R.id.ReEnterPassword);
        create = view.findViewById(R.id.Create);
        hero = view.findViewById(R.id.HeroName);
        reset = view.findViewById(R.id.Reset);
        DataBaseHelper databaseHelper = DataBaseHelper.getDb(getContext());

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName, Password, ReEnterPassword, HeroName;
                UserName = userName.getText().toString();
                Password = password.getText().toString();
                ReEnterPassword = reEnterPassword.getText().toString();
                HeroName = hero.getText().toString();
                List<SignUp> list = databaseHelper.dao().getAllUSers();
                if(list.size()>0) {
                    for (int i = 0; i < list.size(); i++) {

                        if (UserName.equals(list.get(i).getUserName())) {
                            userName.setText("");
                            break;
                        }
                    }
                }
                if (Password.equals(ReEnterPassword)) {
                    databaseHelper.dao().insert(new SignUp(UserName, Password, HeroName));
                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText("");
                password.setText("");
                reEnterPassword.setText("");
                hero.setText("");
            }
        });

        return view;
    }
}
