package com.example.addressbook.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewAddressBook extends AppCompatActivity {
    private EditText firstName, lastName, state, country, zipcode, line1, line2, city, email, PhoneNo;
    private Spinner type, typeEmail, typePhoneNo;
 /*   private Button add, AddEmail, AddPhoneNo, camera, gallery, fetchPhone, fetchAddress, fetchEmail, save, cancel;*/
    private  EditText Nstate, Ncountry, Nzipcode, Nline1, Nline2, Ncity, Nemail, NPhoneNo;
    private Spinner Ntype, NtypeEmail, NtypePhoneNo;
    private ImageView picture;
    private LinearLayout linearLayoutForAddress, linearLayoutForEmail, linearLayoutForPhone;
    int countAddress = 0, countEmail = 0, countPhoneNo = 0;
    boolean flag = false;
    private final static int CAMERA_REQ_CODE = 100;
    private final static int GALLERY_REQ_CODE = 150;
    private CheckFor checkFor = new CheckFor();
    private DataBaseHelper db = DataBaseHelper.getDb(this);
    private UserName username;
    ArrayList<Address> AddressArrayList;
    ArrayList<Email> emailArrayList;
    ArrayList<PhoneNumber> phoneNumberArrayList;
    RecyclerViewAdapter recyclerViewAdapter ;
    Image image;
    Uri uri;
    AddressBookStructureActivity addressBookStructureActivity = new AddressBookStructureActivity();
    int position;
  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_address_book);
       // save = findViewById(R.id.saveButton);
       // cancel = findViewById(R.id.cancelButton);
        /* camera = findViewById(R.id.camera);*/
       // gallery = findViewById(R.id.buttonGallery);
        toolbar = (Toolbar) findViewById(R.id.toolBarForView);
        picture = findViewById(R.id.image1);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        zipcode = findViewById(R.id.zipcode);
        email = findViewById(R.id.emailAddress);
        PhoneNo = findViewById(R.id.phoneNo);
        typePhoneNo = findViewById(R.id.type2);
       // AddPhoneNo = findViewById(R.id.addPhone);
        type = findViewById(R.id.type);
       // add = findViewById(R.id.add);
       // AddEmail = findViewById(R.id.addEmail);
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
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
    }
    public int setSpinnerPostion (String str){
        if (str.equals("office")) {
            return 0;
        } else if (str.equals("home")) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_view,menu);
        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int itemId = item.getItemId();
        if(itemId == R.id.editButton){
            Intent intent = new Intent(this,UpdateAddressBookActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.putExtra("UserName",  username);
            intent.putExtra("ListOfAddress",(Serializable) AddressArrayList);
            intent.putExtra("ListOfEmails",(Serializable) emailArrayList);
            intent.putExtra("ListOfPhoneNo",(Serializable) phoneNumberArrayList);
            intent.putExtra("position",position);
            intent.putExtra("Images", (Serializable) image);
            startActivity(intent);
        }else if(itemId == android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}