package com.example.addressbook.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserNamePhoneNo;

import java.util.List;

public class AddressBookStructure extends AppCompatActivity {
    EditText firstName,lastName,state,country,zipcode,line1,line2,city,email,PhoneNo;
    Spinner type,typeEmail,typePhoneNo;
    Button add,AddEmail,AddPhoneNo,camera,gallery,fetchPhone,fetchAddress,fetchEmail;
    ImageView picture;
    int countAddress=0,countEmail=0,countPhoneNo=0;
    boolean flag=false;
    private   final static int CAMERA_REQ_CODE = 100;
    private   final static int GALLERY_REQ_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book_structure);
        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.buttonGallery);
        picture = findViewById(R.id.image1);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        zipcode = findViewById(R.id.zipcode);
        email =findViewById(R.id.emailAddress);
        PhoneNo = findViewById(R.id.phoneNo);
        typePhoneNo = findViewById(R.id.type2);
        AddPhoneNo = findViewById(R.id.addPhone);
        type = findViewById(R.id.type);
        add = findViewById(R.id.add);
        AddEmail = findViewById(R.id.addEmail);
        city=findViewById(R.id.city);
        line1=findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        fetchPhone = findViewById(R.id.FetchPhone);
        fetchAddress=findViewById(R.id.FetchAddress);
        fetchEmail = findViewById(R.id.FetchEmail);
        typeEmail = findViewById(R.id.type1);
        ArrayAdapter<CharSequence> adapterEmail=ArrayAdapter.createFromResource(this, R.array.type1, android.R.layout.simple_spinner_item);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typeEmail.setAdapter(adapterEmail);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterPhone=ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typePhoneNo.setAdapter(adapterPhone);
        DataBaseHelper db = DataBaseHelper.getDb(this);
        if(firstName.getText().equals("")){
            countAddress =0;
            countEmail=0;
            countPhoneNo =0;
        }
        add.setOnClickListener(new View.OnClickListener() {
            long id;
            int typO,typeH;
            @Override
            public void onClick(View view) {
                flag = true;
                countAddress+=1;
                if(countEmail==0&&countPhoneNo==0){
                if(countAddress==1) {

                    id = db.dao().insertUser(new UserName(firstName.getText().toString(),
                            lastName.getText().toString()));

                    db.dao().insertAddress(new Address(id, type.getSelectedItem().toString(),
                            line1.getText().toString(),
                            line2.getText().toString(), city.getText().toString(),
                            state.getText().toString(), country.getText().toString(),
                            zipcode.getText().toString()));
                }else if(countAddress==2)
                {
                    db.dao().insertAddress(new Address(id, type.getSelectedItem().toString(),
                            line1.getText().toString(),
                            line2.getText().toString(), city.getText().toString(), state.getText().
                            toString(), country.getText().toString(),
                            zipcode.getText().toString()));

                }else{
                    Toast.makeText(AddressBookStructure.this, "CannotInsert", Toast.LENGTH_SHORT).show();

                }
            }else if(countEmail>0&&countPhoneNo>0){

                    id=(long) db.dao().getId(firstName.getText().toString());
                    db.dao().insertAddress(new Address(id, type.getSelectedItem().toString(),
                            line1.getText().toString(),
                            line2.getText().toString(), city.getText().toString(), state.getText().
                            toString(), country.getText().toString(),
                            zipcode.getText().toString()));
                }
            }

        });
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                clean();

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        AddEmail.setOnClickListener(new View.OnClickListener() {
            long id;
            @Override
            public void onClick(View view) {

                countEmail+=1;
                if(countAddress==0&&countPhoneNo==0){
                if(countEmail==1) {

                    id = db.dao().insertUser(new UserName(firstName.getText().toString(),
                            lastName.getText().toString()));

                    db.dao().insertEmail(new Email(id,typeEmail.getSelectedItem().toString(),
                            email.getText().toString()));
                    Toast.makeText(AddressBookStructure.this,String.valueOf(id) , Toast.LENGTH_SHORT).show();
                }else if(countEmail==2)
                {
                    db.dao().insertEmail(new Email(id,typeEmail.getSelectedItem().toString(),
                            email.getText().toString()));

                }else{
                    Toast.makeText(AddressBookStructure.this, "CannotInsert",
                            Toast.LENGTH_SHORT).show();
                    email.setText("");
                }
            }else if(countAddress > 0&&countPhoneNo>0)
                {
                    id=(long)db.dao().getId(firstName.getText().toString());
                    db.dao().insertEmail(new Email(id,typeEmail.getSelectedItem().toString(),
                            email.getText().toString()));

                }
        }
        });
        typeEmail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                email.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        AddPhoneNo.setOnClickListener(new View.OnClickListener() {
            long id;
            @Override
            public void onClick(View view) {

                countPhoneNo+=1;
                if(countAddress==0&&countEmail==0){
                    if(countPhoneNo==1) {

                        id = db.dao().insertUser(new UserName(firstName.getText().toString(),
                                lastName.getText().toString()));

                        db.dao().insertPhone(new PhoneNumber(id,typePhoneNo.getSelectedItem().toString(),
                                PhoneNo.getText().toString()));
                        Toast.makeText(AddressBookStructure.this,String.valueOf(id) , Toast.LENGTH_SHORT).show();
                    }else if(countPhoneNo==2)
                    {
                        db.dao().insertPhone(new PhoneNumber(id,typePhoneNo.getSelectedItem().toString(),
                                PhoneNo.getText().toString()));

                    }else{
                        Toast.makeText(AddressBookStructure.this, "CannotInsert",
                                Toast.LENGTH_SHORT).show();
                        email.setText("");
                    }
                }else if(countAddress > 0&&countEmail>0)
                {
                    if(countPhoneNo<=2){
                    id=(long)db.dao().getId(firstName.getText().toString());
                    db.dao().insertPhone(new PhoneNumber(id,typePhoneNo.getSelectedItem().toString(),
                            PhoneNo.getText().toString()));

                }
                    else{
                        Toast.makeText(AddressBookStructure.this, "limit eceded", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddressBookStructure.this, "limit eceded", Toast.LENGTH_SHORT).show();
                }
            }
        });
        typePhoneNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PhoneNo.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQ_CODE);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQ_CODE);
            }
        });
        fetchPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int id;
               id = db.dao().getId(firstName.getText().toString());
               List<UserNamePhoneNo> ph = db.dao().getPhone(id);
                Toast.makeText(AddressBookStructure.this, String.valueOf(ph.size()), Toast.LENGTH_SHORT).show();
                for(int i=0;i<ph.size();i++) {
                    Log.i("phonenumber", "phone" +
                            ph.get(0).getPhoneList().get(i).getPhonNo().toString() + "type" + ph.get(0).getPhoneList().get(i).getType().toString() +
                            "id" + ph.get(0).getPhoneList().get(i).getId());
                    Log.i("mailid", "Email" +
                            ph.get(1).getEmailList().get(i).getEmail().toString() + "type" + ph.get(1).getEmailList().get(i).getType().toString() +
                            "id" + ph.get(1).getEmailList().get(i).getId());

                }
            }
        });
        fetchAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int id;
                id = db.dao().getId(firstName.getText().toString());
                List<Address> addresses = db.dao().getAddress(id);
                Toast.makeText(AddressBookStructure.this, String.valueOf(addresses.size()), Toast.LENGTH_SHORT).show();
                for(int i=0;i<addresses.size();i++) {
                    Log.i("phonenumber", "phone" +
                            addresses.get(i).getLine1().toString() + "type" + addresses.get(i).getType().toString() + "id" + addresses.get(i).getId()+"");

                }
            }
            }*/
                db.dao().delPhone();
                db.dao().delAddress();
                db.dao().delEmail();
            }});

    }
    public void clean(){
    state.setText("");
        country.setText("");
        zipcode.setText("");
        line1.setText("");
        line2.setText("");
        city.setText("");
    }
    @Nullable
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            if(requestCode == CAMERA_REQ_CODE){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                picture.setImageBitmap(bitmap);
            }
            if(requestCode == GALLERY_REQ_CODE ){
                picture.setImageURI(data.getData());
            }
        }
    }

    }
