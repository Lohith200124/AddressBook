package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.Fragments.LoginPage;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;

import java.util.List;

/**
 * activity which tells us about the passwords
 */
public class ForgotPassword extends AppCompatActivity {
    private  EditText UserName, HeroName,NewPassword,RenterNewPassword;
    private   TextView yourPassword;
    private   Button Next;
    private  DataBaseHelper db = DataBaseHelper.getDb(this);
    private    CheckFor checkFor = new CheckFor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        NewPassword = findViewById(R.id.NewPassword1);
        RenterNewPassword = findViewById(R.id.ReEnterNewPassword);
        UserName = findViewById(R.id.EmailAddressForForgotPassword);
        HeroName = findViewById(R.id.heroName);
        Next = findViewById(R.id.next);
        yourPassword = findViewById(R.id.Yourpassword);
        List<SignUp> list = db.dao().getAllUSers();
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (list.size() > 0) {
                        for (int i = 0; i <list.size(); i++) {
                            if ((UserName.getText().toString()).equals(list.get(i).getUserName())) {
                                if(checkFor.passwordValid(RenterNewPassword.getText().toString())){
                                    db.dao().update(new SignUp(UserName.getText().toString(),RenterNewPassword.getText().toString(),HeroName.getText().toString(),
                                            list.get(i).getFirstName(),list.get(i).getLastName()));
                                    Intent intent = new Intent(ForgotPassword.this, ActivtyLoginFragments.class);
                                    startActivity(intent);
                                }

                                //yourPassword.setText(db.dao().getPassword(UserName.getText().toString()));

                                //Toast.makeText(ForgotPassword.this, "enterded", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            //Toast.makeText(ForgotPassword.this, "enterded1", Toast.LENGTH_SHORT).show();
                        }

                    }
            }
        });


    }
}