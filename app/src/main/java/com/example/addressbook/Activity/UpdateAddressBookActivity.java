package com.example.addressbook.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;

import java.util.ArrayList;
import java.util.List;
//import com.example.addressbook.db.UserName;

public class UpdateAddressBookActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private   EditText firstName, lastName, state, country, zipcode, line1, line2, city, email, PhoneNo;
    private   Spinner type, typeEmail, typePhoneNo;
    private  Button add, AddEmail, AddPhoneNo, camera, gallery, fetchPhone, fetchAddress, fetchEmail, save, cancel;
    private  EditText Nstate, Ncountry, Nzipcode, Nline1, Nline2, Ncity, Nemail, NPhoneNo;
    private Spinner Ntype, NtypeEmail, NtypePhoneNo;
    private ImageView picture;
    private  LinearLayout linearLayoutForAddress, linearLayoutForEmail, linearLayoutForPhone;
    int countAddress = 0, countEmail = 0, countPhoneNo = 0;
    boolean flag = false;
    private final static int CAMERA_REQ_CODE = 100;
    private final static int GALLERY_REQ_CODE = 150;
    private CheckFor checkFor = new CheckFor();
    private  DataBaseHelper db = DataBaseHelper.getDb(this);
    private UserName username;
    ArrayList<Address> AddressArrayList;
    ArrayList<Email> emailArrayList;
    ArrayList<PhoneNumber> phoneNumberArrayList;
    RecyclerViewAdapter recyclerViewAdapter;
    Image image;
    Uri uri;
    int position;
    // ArrayList<UserInfo> AddressArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address_book);
        save = findViewById(R.id.saveButton);
        cancel = findViewById(R.id.cancelButton);
       /* camera = findViewById(R.id.camera);*/
        gallery = findViewById(R.id.buttonGallery);
        picture = findViewById(R.id.image1);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        zipcode = findViewById(R.id.zipcode);
        email = findViewById(R.id.emailAddress);
        PhoneNo = findViewById(R.id.phoneNo);
        typePhoneNo = findViewById(R.id.type2);
        AddPhoneNo = findViewById(R.id.addPhone);
        type = findViewById(R.id.type);
        add = findViewById(R.id.add);
        AddEmail = findViewById(R.id.addEmail);
        city = findViewById(R.id.city);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
    /*    fetchPhone = findViewById(R.id.FetchPhone);
        fetchAddress = findViewById(R.id.FetchAddress);
        fetchEmail = findViewById(R.id.FetchEmail);*/
        typeEmail = findViewById(R.id.type1);
        ArrayAdapter<CharSequence> adapterEmail = ArrayAdapter.createFromResource(this, R.array.type1, android.R.layout.simple_spinner_item);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typeEmail.setAdapter(adapterEmail);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterPhone = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typePhoneNo.setAdapter(adapterPhone);
        Nline1 = findViewById(R.id.newline1);
        Nline2 = findViewById(R.id.newline2);
        Ncity = findViewById(R.id.newcity);
        Nstate = findViewById(R.id.newstate);
        Ncountry = findViewById(R.id.newcountry);
        Nzipcode = findViewById(R.id.newzipcode);
        Nemail = findViewById(R.id.newemailAddress);
        NPhoneNo = findViewById(R.id.newphoneNo);
        Ntype = findViewById(R.id.newtype);
        NtypeEmail = findViewById(R.id.newtype1);
        NtypePhoneNo = findViewById(R.id.newtype2);
        ArrayAdapter<CharSequence> NadapterEmail = ArrayAdapter.createFromResource(this, R.array.type1, android.R.layout.simple_spinner_item);
        NadapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NtypeEmail.setAdapter(NadapterEmail);
        ArrayAdapter<CharSequence> Nadapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        Nadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Ntype.setAdapter(Nadapter);
        ArrayAdapter<CharSequence> NadapterPhone = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        NadapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NtypePhoneNo.setAdapter(NadapterPhone);
        DataBaseHelper db = DataBaseHelper.getDb(this);
        username = (UserName) getIntent().getSerializableExtra("UserName");
        firstName.setText(username.getFirstName().toString());
        lastName.setText(username.getLastName().toString());

        linearLayoutForAddress = findViewById(R.id.AddressLinearLayout);
        linearLayoutForPhone = findViewById(R.id.phoneLinearLayout);
        linearLayoutForEmail = findViewById(R.id.EmailLayout);
        //AddressArrayList = (ArrayList<UserInfo>) getIntent().getSerializableExtra("ListOfAddress");
        AddressArrayList = (ArrayList<Address>) getIntent().getSerializableExtra("ListOfAddress");
        for (int i = 0; i <= AddressArrayList.size() - 1; i++) {
            if (i == AddressArrayList.size() - 1 && AddressArrayList.size() > 1) {
                linearLayoutForAddress.setVisibility(View.VISIBLE);
                Nline1.setText(AddressArrayList.get(i).getLine1());
                Nline2.setText(AddressArrayList.get(i).getLine2());
                Nstate.setText(AddressArrayList.get(i).getState());
                Ncountry.setText(AddressArrayList.get(i).getCountry());
                Nzipcode.setText(AddressArrayList.get(i).getPincode());
                Ncity.setText(AddressArrayList.get(i).getCity());
                Ntype.setSelection(setSpinnerPostion(AddressArrayList.get(i).getType()));
                countAddress+=1;
            } else {
                line1.setText(AddressArrayList.get(i).getLine1());
                line2.setText(AddressArrayList.get(i).getLine2());
                state.setText(AddressArrayList.get(i).getState());
                country.setText(AddressArrayList.get(i).getCountry());
                zipcode.setText(AddressArrayList.get(i).getPincode());
                city.setText(AddressArrayList.get(i).getCity());
                type.setSelection(setSpinnerPostion(AddressArrayList.get(i).getType()));
            }

        }
        emailArrayList = (ArrayList<Email>) getIntent().getSerializableExtra("ListOfEmails");
        for (int i = 0; i <= emailArrayList.size() - 1; i++) {
            if (i == emailArrayList.size() - 1 && emailArrayList.size() > 1) {
                linearLayoutForEmail.setVisibility(View.VISIBLE);
                Nemail.setText(emailArrayList.get(i).getEmail());
                NtypeEmail.setSelection(setSpinnerPostion(emailArrayList.get(i).getType()));
          countEmail+=1;  } else {
                email.setText(emailArrayList.get(i).getEmail());
                typeEmail.setSelection(setSpinnerPostion(emailArrayList.get(i).getType()));

            }
        }
        phoneNumberArrayList = (ArrayList<PhoneNumber>) getIntent().getSerializableExtra("ListOfPhoneNo");
        for (int i = 0; i <= phoneNumberArrayList.size() - 1; i++) {
            if (i == phoneNumberArrayList.size() - 1 && phoneNumberArrayList.size() > 1) {
                linearLayoutForPhone.setVisibility(View.VISIBLE);
                NPhoneNo.setText(phoneNumberArrayList.get(i).getPhonNo());
                NtypePhoneNo.setSelection(setSpinnerPostion((phoneNumberArrayList.get(i).getType())));
            countPhoneNo+=1;} else {
                PhoneNo.setText(phoneNumberArrayList.get(i).getPhonNo());
                typePhoneNo.setSelection(setSpinnerPostion(phoneNumberArrayList.get(i).getType()));
            }
        }
        image = (Image) getIntent().getSerializableExtra("Images");
        if (image != null) {
            uri = Uri.parse(image.getUri());
            picture.setImageURI(uri);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countAddress += 1;
                linearLayoutForAddress.setVisibility(View.VISIBLE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
        position = (int) getIntent().getSerializableExtra("position");
        save.setOnClickListener(new View.OnClickListener() {
            String emailStr, phonNoStr, line1Str, stateStr, countryStr, zipcodeStr, line2Str, cityStr;
            long id = 0;

            @Override
            public void onClick(View view) {
                emailStr = email.getText().toString();
                phonNoStr = PhoneNo.getText().toString();
                line1Str = line1.getText().toString();
                line2Str = line2.getText().toString();
                cityStr = city.getText().toString();
                stateStr = state.getText().toString();
                countryStr = country.getText().toString();
                zipcodeStr = zipcode.getText().toString();
                if (line1Str.equals("") || cityStr.equals("") || stateStr.equals("") ||
                        countryStr.equals("") || zipcodeStr.equals("") || emailStr.equals("") || phonNoStr.equals("")
                ) {
                    Toast.makeText(UpdateAddressBookActivity.this, "make sure you enter all fields and follow the rules", Toast.LENGTH_SHORT).show();

                } else
                {
                    if (AddressArrayList.size() >= 2 && emailArrayList.size() >= 2 &&
                            phoneNumberArrayList.size() >= 2) {
                        if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString())) {
                            Toast.makeText(UpdateAddressBookActivity.this, "check the type one", Toast.LENGTH_SHORT).show();
                        } else {
                            updateBasic();
                            NaddressUpdate();
                            NemailUpdate();
                            NphoneNoUpdate();

                        }
                    }
                    else if(AddressArrayList.size() >= 2 && emailArrayList.size() >= 2 &&
                            phoneNumberArrayList.size() >= 1)
                    {
                        if (countPhoneNo >= 1)
                        {
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        } else
                        {
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()))
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                            }
                        }
                    }
                    else if(AddressArrayList.size() >= 2 && emailArrayList.size() >= 1 &&
                            phoneNumberArrayList.size() >= 1)
                    {

                        if(countPhoneNo>=1&&countEmail>=1)
                        {
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }
                        else if(countPhoneNo>=1&&countEmail>=0){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                updateBasic();
                                NaddressUpdate();
                                NphoneNoUpdate();
                            }
                        }
                        else if(countPhoneNo>=0&&countEmail>=1){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                updateBasic();
                                NaddressUpdate();
                                NphoneNoUpdate();
                            }
                        }
                        else{
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                            }
                        }
                    }
                    else if(AddressArrayList.size() >= 1 && emailArrayList.size() >= 1 &&
                            phoneNumberArrayList.size() >= 1)
                    {
                     if(countAddress>=1&&countEmail>=1&&countPhoneNo>=1){
                         if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                 (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                 (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NaddressUpdate();
                             NemailUpdate();
                             NphoneNoUpdate();
                         }
                     }else if(countAddress>=1&&countEmail>=1&&countPhoneNo>=0){
                         if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                 (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()))
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NaddressUpdate();
                             NemailUpdate();
                         }
                     }else if(countAddress>=1&&countEmail>=0&&countPhoneNo>=1){
                         if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                 (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NaddressUpdate();
                             NphoneNoUpdate();
                         }
                     }else if(countAddress>=0&&countEmail>=1&&countPhoneNo>=1){
                         if ((typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                 (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NemailUpdate();
                             NphoneNoUpdate();
                         }
                     }else if(countAddress>=1&&countEmail>=0&&countPhoneNo>=0){
                         if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) )
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NaddressUpdate();
                         }
                     }else if (countAddress>=0&&countEmail>=1&&countPhoneNo>=0){
                         if ((typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()))
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NemailUpdate();
                         }
                     }else if(countAddress>=0&&countEmail>=0&&countPhoneNo>=1){
                         if ((typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                         {
                             Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             updateBasic();
                             NphoneNoUpdate();
                         }
                     }else
                     {
                         updateBasic();
                     }
                    }
                    else if(AddressArrayList.size() >= 1 && emailArrayList.size() >= 2 &&
                            phoneNumberArrayList.size() >= 1){
                        if(countAddress>=1&&countPhoneNo>=1){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }else if(countAddress>=1&&countPhoneNo>=0){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                            }
                        }else if(countAddress>=0&&countPhoneNo>=1){
                            if ((typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }else if(countAddress>=0&&countPhoneNo>=0){
                            updateBasic();
                        }
                    }
                    else if(AddressArrayList.size() >= 2 && emailArrayList.size() >= 1 &&
                            phoneNumberArrayList.size() >= 2){
                        if(countEmail>=1){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }else {
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NphoneNoUpdate();
                            }
                        }

                    }
                    else if(AddressArrayList.size() >= 1 && emailArrayList.size() >= 2 &&
                            phoneNumberArrayList.size() >= 2){
                        if(countAddress>=1){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }else
                        {
                            if ((typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }
                    }
                    else if(AddressArrayList.size() >= 2 && emailArrayList.size() >= 2 &&
                            phoneNumberArrayList.size() >= 1){
                        if(countPhoneNo>=1){
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();
                                NphoneNoUpdate();
                            }
                        }
                        else{
                            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()))
                            {
                                Toast.makeText(UpdateAddressBookActivity.this, "already exisit", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                updateBasic();
                                NaddressUpdate();
                                NemailUpdate();

                            }
                        }
                    }
                    /*if (countAddress == 0 && countEmail == 0 && countPhoneNo == 0) {
                 *//*if ((line1.getText().toString()).equals(AddressArrayList.get(i).getLine1()) &&
                         (line2.getText().toString()).equals(AddressArrayList.get(i).getLine2()) &&
                         ( city.getText().toString()).equals(AddressArrayList.get(i).getCity()) &&
                         (  state.getText().toString()).equals(AddressArrayList.get(i).getState()) &&
                         ( country.getText().toString()).equals(AddressArrayList.get(i).getCountry()) &&
                         (  zipcode.getText().toString()).equals(AddressArrayList.get(i).getCountry()))
                     {
                        Toast.makeText(UpdateAddressBookActivity.this, "no changes",
                                Toast.LENGTH_SHORT).show();
                   }
                              counterAddress+=1;
                        }*//*
                        if (AddressArrayList.size() >= 2) {
                            id = idOfUser();
                            updateBasic();
                            db.dao().updateAddress(new Address(db.dao().getAddressId(id, Ntype.getSelectedItem().toString()),
                                    id, Ntype.getSelectedItem().toString(),
                                    Nline1.getText().toString(), Nline2.getText().toString(),
                                    Ncity.getText().toString(), Nstate.getText().toString(),
                                    Ncountry.getText().toString(), Nzipcode.getText().toString()));

                        } else {
                            updateBasic();*/

              }
try {
    updateRecyclerView();
}catch (NullPointerException e){
    e.printStackTrace();
}
            }
        });
        AddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countEmail+=1;
                linearLayoutForEmail.setVisibility(View.VISIBLE);
            }
        });
                AddPhoneNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countPhoneNo+=1;
                        linearLayoutForPhone.setVisibility(View.VISIBLE);
                    }
                });
    }

    /**
     * to set spinner postion while displaying
     * @param str
     * @return
     */
        public int setSpinnerPostion (String str){
            if (str.equals("office")) {
                return 0;
            } else if (str.equals("home")) {
                return 1;
            }
            return -1;
        }
   /* private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(UpdateAddressBookActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(UpdateAddressBookActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(UpdateAddressBookActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(UpdateAddressBookActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    uri = Uri.parse(image.getUri());
                    picture.setImageURI(uri);
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }*/
  //ArrayList<UserName> list;

    /**
     * to get id of user and insert if not inserted
     * @return
     */
    public long idOfUser(){
        long id;
        int localCounter=0;
        ArrayList<UserName> list = (ArrayList<UserName>) db.dao().getAllUsers();
        for(int i=0;i<list.size();i++){
            if((firstName.getText().toString()).equals(list.get(i).getFirstName())){
                id = db.dao().getId(firstName.getText().toString());
                return id;
            }
            localCounter++;
        }
        if(localCounter == list.size())  {

            id = db.dao().insertUser(new UserName(firstName.getText().toString(), lastName.getText().toString()));
            return  id;
        }
        //list.removeAll();
        return  0;
    }

    /**
     * makes the counters to zero
     */
    public void clean(){
        /*state.setText("");
        country.setText("");
        zipcode.setText("");
        line1.setText("");
        line2.setText("");
        city.setText("");
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        PhoneNo.setText("");
        Nstate.setText("");
        Ncountry.setText("");Nzipcode.setText("");
        Nline1.setText("");Nline2.setText("");
        Ncity.setText("");Nemail.setText("");
        NPhoneNo.setText("");
        linearLayoutForEmail.setVisibility(View.INVISIBLE);
        linearLayoutForPhone.setVisibility(View.INVISIBLE);
        linearLayoutForAddress.setVisibility(View.INVISIBLE);*/
        // linearLayoutForAddress.removeAllViews();
        countAddress=0;countEmail=0;
        countPhoneNo=0;

    }

    /**
     *updates the primary fields
     */
    public void updateBasic(){
        long id;
        id = idOfUser();

          //  db.dao().updateName(new UserName((int) id, lastName.getText().toString()));

            db.dao().updateAddress(new Address(db.dao().getAddressId(id, type.getSelectedItem().toString()),
                    id, type.getSelectedItem().toString(),
                    line1.getText().toString(), line2.getText().toString(),
                    city.getText().toString(), state.getText().toString(),
                    country.getText().toString(), zipcode.getText().toString()));
            if (checkFor.validateEmail(email.getText().toString())) {
                db.dao().updateEmail(new Email(db.dao().getEmailId(id, typeEmail.getSelectedItem().toString()),
                        id, typeEmail.getSelectedItem().toString(), email.getText().toString()));
            } else {
                Toast.makeText(this, "check email", Toast.LENGTH_SHORT).show();
            }
            db.dao().updatePhoneNo(new PhoneNumber(db.dao().getPhonenoId(id, typePhoneNo.getSelectedItem().toString()),
                    id, typePhoneNo.getSelectedItem().toString(), PhoneNo.getText().toString()));


        }

    /**
     * checks firstname and returns false if exist else true
     * @param str
     * @return bool
     */
//    public boolean firstnameCheck(String str){
//        int firstnameCounter=0;
//        List<UserName> listFirstname = db.dao().getAllUsers();
//        for(int i=0;i< listFirstname.size();i++){
//            if((firstName.getText().toString()).equals(listFirstname.get(i).getFirstName())){
//                //Toast.makeText(this, "firstname already exist pls change", Toast.LENGTH_SHORT).show();
//            return true;
//            }
//            firstnameCounter+=1;
//        }
//        if(firstnameCounter>= listFirstname.size()){
//            return false;
//        }
//        return false;
//    }
    /**
     * to update the second  address
     */
    public void NaddressUpdate(){
        long id;
        id= idOfUser();
            db.dao().updateAddress(new Address(db.dao().getAddressId(id, Ntype.getSelectedItem().toString()),
                    id, Ntype.getSelectedItem().toString(),
                    Nline1.getText().toString(), Nline2.getText().toString(),
                    Ncity.getText().toString(), Nstate.getText().toString(),
                    Ncountry.getText().toString(), Nzipcode.getText().toString()));


    }
    /**
     * to update the second  email
     */
        public void  NemailUpdate() {
            long id;
            id = idOfUser();

                if (checkFor.validateEmail(Nemail.getText().toString())) {
                    db.dao().updateEmail(new Email(db.dao().getEmailId(id, NtypeEmail.getSelectedItem().toString()),
                            id, NtypeEmail.getSelectedItem().toString(),
                            Nemail.getText().toString()));
                } else {
                    Toast.makeText(this, "check format", Toast.LENGTH_SHORT).show();
                }

        }

    /**
     * to update the second  phonenumber
     */
    public void NphoneNoUpdate() {
        long id;
        id = idOfUser();

            db.dao().updatePhoneNo(new PhoneNumber(db.dao().getPhonenoId(id, NtypePhoneNo.getSelectedItem().toString()),
                    id, NtypePhoneNo.getSelectedItem().toString(),
                    NPhoneNo.getText().toString()));

    }

    /**
     * to tell the recyclerview that values are updated
     */
    public void updateRecyclerView(){
            recyclerViewAdapter.userInfo.set(position,new UserInfo(new UserName((int)idOfUser(),
                    firstName.getText().toString(),lastName.getText().toString()),
                    new Image()
                    ,db.dao().getPhoneList((int)idOfUser()),db.dao().getEmail((int)idOfUser()),
                    db.dao().getAddress((int)idOfUser()) ));
            recyclerViewAdapter.notifyItemChanged(position);
        }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str;
        Uri uri;
        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQ_CODE ){
                uri =  data.getData();
                str = uri.toString();
                db.dao().insertImage(new Image(str,idOfUser()));
                picture.setImageURI(data.getData());
            }
        }
    }
    }

