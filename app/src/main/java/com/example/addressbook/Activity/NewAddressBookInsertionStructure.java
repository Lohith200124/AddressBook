package com.example.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.addressbook.Fragments.AddressFragment;
import com.example.addressbook.Fragments.EmailAndPhoneNumberFragment;
import com.example.addressbook.R;

public class NewAddressBookInsertionStructure extends AppCompatActivity {
    Button start,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address_book_insertion_structure);
       /* next = findViewById(R.id.nextFragment);
        start = findViewById(R.id.startFragment);*/
      /*  next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {*/
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.new_insertion_structure,new AddressFragment());
                ft.commit();
          /*  }
        });*/
        /*start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.new_insertion_structure,new EmailAndPhoneNumberFragment());
                ft.commit();
            }
        });*/
    }
}