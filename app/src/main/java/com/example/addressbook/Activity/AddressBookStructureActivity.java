package com.example.addressbook.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.addressbook.Fragments.HomeFragment;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 *to insert the values it is used for creation
 */
public class AddressBookStructureActivity extends AppCompatActivity {
 private    EditText firstName,lastName,state,country,zipcode,line1,line2,city,email,PhoneNo;
    private  Spinner type,typeEmail,typePhoneNo;
    private  Button add,AddEmail,AddPhoneNo,gallery,save,cancel;
    private   EditText Nstate,Ncountry,Nzipcode,Nline1,Nline2,Ncity,Nemail,NPhoneNo;
    private  Spinner Ntype,NtypeEmail,NtypePhoneNo;
    private  ImageView picture;
    private  LinearLayout linearLayoutForAddress,linearLayoutForEmail,linearLayoutForPhone;
    int countAddress=0,countEmail=0,countPhoneNo=0;
    private   final static int GALLERY_REQ_CODE = 150;
    CheckFor checkFor = new CheckFor();
    DataBaseHelper db = DataBaseHelper.getDb(this);
    int saveButtonCount=0;
    String item;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book_structure);
        save = findViewById(R.id.saveButton);
        cancel = findViewById(R.id.cancelButton);
        linearLayoutForAddress = findViewById(R.id.AddressofUser);
        linearLayoutForEmail = findViewById(R.id.EmailLayout);
        linearLayoutForPhone = findViewById(R.id.phoneLinearLayout);
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
        Nline1= findViewById(R.id.newline1);
        Nline2 = findViewById(R.id.newline2);
        Ncity = findViewById(R.id.newcity);
        Nstate = findViewById(R.id.newstate);
        Ncountry = findViewById(R.id.newcountry);
        Nzipcode = findViewById(R.id.newzipcode);
        Nemail = findViewById(R.id.newemailAddress);
        NPhoneNo = findViewById(R.id.newphoneNo);
        Ntype = (Spinner) findViewById(R.id.newtype);
        NtypeEmail = (Spinner)findViewById(R.id.newtype1);
        NtypePhoneNo = (Spinner)findViewById(R.id.newtype2);
        ArrayAdapter<CharSequence> NadapterEmail=ArrayAdapter.createFromResource(this, R.array.type1, android.R.layout.simple_spinner_item);
        NadapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NtypeEmail.setAdapter(NadapterEmail);
        ArrayAdapter<CharSequence> Nadapter=ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        Nadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Ntype.setAdapter(Nadapter);
        ArrayAdapter<CharSequence> NadapterPhone=ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        NadapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NtypePhoneNo.setAdapter(NadapterPhone);

        /*if(firstName.getText().equals("")){
            countAddress =0;
            countEmail=0;
            countPhoneNo =0;
        }*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    flag = true;
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
                    Toast.makeText(AddressBookStructureActivity.this, "CannotInsert", Toast.LENGTH_SHORT).show();

                }
            }else if(countEmail>0&&countPhoneNo>0){

                    id=(long) db.dao().getId(firstName.getText().toString());
                    db.dao().insertAddress(new Address(id, type.getSelectedItem().toString(),
                            line1.getText().toString(),
                            line2.getText().toString(), city.getText().toString(), state.getText().
                            toString(), country.getText().toString(),
                            zipcode.getText().toString()));
                }
            }*/
                countAddress+=1;
                linearLayoutForAddress.setVisibility(View.VISIBLE);
            }});
        AddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countEmail+=1;linearLayoutForEmail.setVisibility(View.VISIBLE);
            }
        });
        /*type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                clean();

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });*/
        /*AddEmail.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AddressBookStructureActivity.this,String.valueOf(id) , Toast.LENGTH_SHORT).show();
                }else if(countEmail==2)
                {
                    db.dao().insertEmail(new Email(id,typeEmail.getSelectedItem().toString(),
                            email.getText().toString()));

                }else{
                    Toast.makeText(AddressBookStructureActivity.this, "CannotInsert",
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
        });*/
        /*typeEmail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                email.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        AddPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countPhoneNo+=1;
                linearLayoutForPhone.setVisibility(View.VISIBLE);
             /*   countPhoneNo+=1;
                if(countAddress==0&&countEmail==0){
                    if(countPhoneNo==1) {

                        id = db.dao().insertUser(new UserName(firstName.getText().toString(),
                                lastName.getText().toString()));

                        db.dao().insertPhone(new PhoneNumber(id,typePhoneNo.getSelectedItem().toString(),
                                PhoneNo.getText().toString()));
                        Toast.makeText(AddressBookStructureActivity.this,String.valueOf(id) , Toast.LENGTH_SHORT).show();
                    }else if(countPhoneNo==2)
                    {
                        db.dao().insertPhone(new PhoneNumber(id,typePhoneNo.getSelectedItem().toString(),
                                PhoneNo.getText().toString()));

                    }else{
                        Toast.makeText(AddressBookStructureActivity.this, "CannotInsert",
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
                        Toast.makeText(AddressBookStructureActivity.this, "limit eceded", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddressBookStructureActivity.this, "limit eceded", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        /*typePhoneNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PhoneNo.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
       /* camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQ_CODE);
            }
        });*/
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQ_CODE);
            }
        });
        /*fetchPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int id;
               id = db.dao().getId(firstName.getText().toString());
               List<UserNamePhoneNo> ph = db.dao().getPhone(id);
                Toast.makeText(AddressBookStructureActivity.this, String.valueOf(ph.size()), Toast.LENGTH_SHORT).show();
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
                *//*int id;
                id = db.dao().getId(firstName.getText().toString());
                List<Address> addresses = db.dao().getAddress(id);
                Toast.makeText(AddressBookStructureActivity.this, String.valueOf(addresses.size()), Toast.LENGTH_SHORT).show();
                for(int i=0;i<addresses.size();i++) {
                    Log.i("phonenumber", "phone" +
                            addresses.get(i).getLine1().toString() + "type" + addresses.get(i).getType().toString() + "id" + addresses.get(i).getId()+"");

                }
            }
            }*//*
                db.dao().delPhone();
                db.dao().delUserName1();
                db.dao().delEmail();
                db.dao().delImage();
                db.dao().delAddress();
            }});*/
       //int saveButtonCount=0;
        save.setOnClickListener(new View.OnClickListener() {
            String emailStr,phonNoStr,line1Str,stateStr,countryStr,zipcodeStr,line2Str,cityStr;
           long id;
            @Override
            public void onClick(View view) {
saveButtonCount+=1;
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
        Toast.makeText(AddressBookStructureActivity.this, "make sure you enter all fields and follow the rules", Toast.LENGTH_SHORT).show();
        clean();
    }
    else {
        if(saveButtonCount<=1) {
        if (countAddress == 0 && countEmail == 0 && countPhoneNo == 0) {
            insertBasic();
                          /*  homeFragment.lis
                            recyclerViewAdapter.notifyItemInserted();*/

        } else if (countAddress >= 1 && countEmail >= 1 && countPhoneNo >= 1) {
            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString())) {

                Toast.makeText(AddressBookStructureActivity.this, "check the type one", Toast.LENGTH_SHORT).show();
            } else {
                if (checkFor.validateEmail(Nemail.getText().toString())) {
                           /* try {
                                id = db.dao().insertUser(new UserName(firstName.getText().toString(), lastName.getText().toString()));
                            } catch (Exception e) {

                                Toast.makeText(AddressBookStructureActivity.this, "first name already exist", Toast.LENGTH_SHORT).show();
                                firstName.setText("");
                            }*/
                           /* db.dao().insertAddress(new Address(id, Ntype.getSelectedItem().toString(), Nline1.getText().toString(), Nline2.getText().toString(),
                                    Ncity.getText().toString(), Nstate.getText().toString(), Ncountry.getText().toString(), Nzipcode.getText().toString()));
                            db.dao().insertEmail(new Email(id, NtypeEmail.getSelectedItem().toString(), Nemail.getText().toString()));
                            db.dao().insertPhone(new PhoneNumber(id, NtypePhoneNo.getSelectedItem().toString(), NPhoneNo.getText().toString()));*/
                    id = idOfUser();
                    insertBasic();
                    insertNAddress(id);
                    insertNEmail(id);
                    insertNPhoneNo(id);

                    //Toast.makeText(AddressBookStructureActivity.this, "entered", Toast.LENGTH_SHORT).show();

                } else {
                    email.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (countAddress >= 1 && countEmail == 0 && countPhoneNo == 0) {
            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString())) {
                Toast.makeText(AddressBookStructureActivity.this, "check the type two", Toast.LENGTH_SHORT).show();
            } else {
                if (checkFor.validateEmail(email.getText().toString())) {
                    id = idOfUser();
                    insertBasic();
                    insertNAddress(id);

                } else {
                    Nemail.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (countAddress == 0 && countEmail >= 1 && countPhoneNo == 0) {
            if ((typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString())) {

                Toast.makeText(AddressBookStructureActivity.this, "check the type three", Toast.LENGTH_SHORT).show();
            } else {
                if (checkFor.validateEmail(Nemail.getText().toString())) {
                    id = idOfUser();
                    insertBasic();
                    insertNEmail(id);

                } else {
                    Nemail.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention ", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (countAddress == 0 && countEmail == 0 && countPhoneNo >= 1) {
            if ((typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString())) {
                Toast.makeText(AddressBookStructureActivity.this, "check the type four", Toast.LENGTH_SHORT).show();

            } else {
                if (checkFor.validateEmail(email.getText().toString())) {
                    id = idOfUser();
                    insertBasic();
                    insertNPhoneNo(id);

                } else {
                    Nemail.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (countAddress >= 1 && countEmail >= 1 && countPhoneNo == 0) {
            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                    (typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString())) {
                Toast.makeText(AddressBookStructureActivity.this, "check the type five", Toast.LENGTH_SHORT).show();
            } else {
                if (checkFor.validateEmail(Nemail.getText().toString())) {
                    id = idOfUser();
                    insertBasic();
                    insertNAddress(id);
                    insertNEmail(id);

                } else {
                    Nemail.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (countAddress == 0 && countEmail >= 1 && countPhoneNo >= 1) {
            if ((typeEmail.getSelectedItem().toString()).equals(NtypeEmail.getSelectedItem().toString()) &&
                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString())) {
                Toast.makeText(AddressBookStructureActivity.this, "check the type six", Toast.LENGTH_SHORT).show();
            } else {
                if (checkFor.validateEmail(Nemail.getText().toString())) {
                    id = idOfUser();
                    insertBasic();
                    insertNEmail(id);
                    insertNPhoneNo(id);

                } else {
                    Nemail.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (countAddress >= 1 && countEmail == 0 && countPhoneNo >= 1) {
            if ((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString()) &&
                    (typePhoneNo.getSelectedItem().toString()).equals(NtypePhoneNo.getSelectedItem().toString())) {
                Toast.makeText(AddressBookStructureActivity.this, "check the type seven", Toast.LENGTH_SHORT).show();

            } else {
                if (checkFor.validateEmail(email.getText().toString())) {
                    id = idOfUser();
                    insertBasic();
                    insertNAddress(id);
                    insertNPhoneNo(id);

                } else {
                    Nemail.setText("");
                    Toast.makeText(AddressBookStructureActivity.this, "follow the email convention seven", Toast.LENGTH_SHORT).show();
                }
            }
        }
        Intent intent = new Intent(AddressBookStructureActivity.this,HomePageActivity.class);
        startActivity(intent);

            try {
                updateRecyclerView();
                Toast.makeText(AddressBookStructureActivity.this, "inserted item", Toast.LENGTH_SHORT).show();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
    }

    else{
            Toast.makeText(AddressBookStructureActivity.this, "move to update", Toast.LENGTH_SHORT).show();
        }
}
            }});
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
                saveButtonCount =0 ;
            }
        });

    }

    /**
     * to insert single user
     */
    public void insertBasic(){
        String emailStr,phonNoStr,line1Str,stateStr,countryStr,zipcodeStr,line2Str,cityStr;
        CheckFor checkFor = new CheckFor();
        List<UserName> list;
        long id=0;
        ArrayList<Address> addressArrayList ;
        ArrayList<Email> EmailList ;
        ArrayList<PhoneNumber> phoneList ;
        emailStr = email.getText().toString();
        phonNoStr = PhoneNo.getText().toString();
        line1Str = line1.getText().toString();
        line2Str = line2.getText().toString();
        cityStr = city.getText().toString();
        stateStr = state.getText().toString();
        countryStr = country.getText().toString();
        zipcodeStr = zipcode.getText().toString();
        list = db.dao().getAllUsers();
        if (checkFor.phoneNumberValidation(phonNoStr)) {
            if (checkFor.validateEmail(emailStr)) {
            /*for(int i=0;i<list.size();i++){
                if((firstName.getText().toString()).equals(list.get(i).getFirstName())){
                    id = db.dao().getId(firstName.getText().toString());
                    localCounter++;
                }
            }
            if(localCounter == list.size())  {

                    id = db.dao().insertUser(new UserName(firstName.getText().toString(), lastName.getText().toString()));
            }*/
                id = idOfUser();
                addressArrayList = (ArrayList<Address>) db.dao().getAddress((int) id);
                if (AddressCheck(addressArrayList)) {
                    db.dao().insertAddress(new Address(id, type.getSelectedItem().toString(), line1Str, line2Str,
                            cityStr, stateStr, countryStr, zipcodeStr));
                }
                EmailList = (ArrayList<Email>) db.dao().getEmail((int) id);
                if (EmailCheck(EmailList)) {
                    db.dao().insertEmail(new Email(id, typeEmail.getSelectedItem().toString(), emailStr));
                }
                phoneList = (ArrayList<PhoneNumber>) db.dao().getPhoneList((int) id);
                if (PhoneNoCheck(phoneList)) {

                    db.dao().insertPhone(new PhoneNumber(id, typePhoneNo.getSelectedItem().toString(), phonNoStr));

                }
            } else {
                email.setText("");
                Toast.makeText(AddressBookStructureActivity.this, "follow the email convention", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(AddressBookStructureActivity.this, "phone", Toast.LENGTH_SHORT).show();
        }
    }
    ArrayList<UserName> list;

    /**
     * to get id of user
     * two caSES IF INSERTED RETURNS ID ELSE IT WILL INSERT AND RETURNS ID
     * @return id
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
     * to check duplicate types in the addrress
     * @param list
     * @return bool
     */
    public boolean AddressCheck(ArrayList<Address> list){
        int counter=0;
       if(list.size()>=2){
           Toast.makeText(this, "full", Toast.LENGTH_SHORT).show();
       }else if(list.size() <= 1) {
           for(int i=0;i<list.size();i++){
              /* Ntype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       item = String.valueOf(Ntype.getItemAtPosition(Ntype.getSelectedItemPosition()));
                       //return item;
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });*/
               if ((list.get(i).getType()).equals(Ntype.getSelectedItem().toString())){
                   Toast.makeText(this, "type already exist in address", Toast.LENGTH_SHORT).show();
                   return  false;
               }
               counter+=1;
           }
               if(counter==list.size())
               {
                   return  true;
               }
       }
       return true;
    }

    /**
     * to insert another or second address
     * @param id
     */
    public void insertNAddress(long id){
        ArrayList<Address> addressArrayList = (ArrayList<Address>) db.dao().getAddress((int)id);
        if(AddressCheck(addressArrayList)){
        db.dao().insertAddress(new Address(id, Ntype.getSelectedItem().toString(), Nline1.getText().toString(), Nline2.getText().toString(),
                Ncity.getText().toString(), Nstate.getText().toString(), Ncountry.getText().toString(), Nzipcode.getText().toString()));

    } else{
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  to check the type of the field and to stop the duplicate types in email
     * @param list
     * @return bool
     */
    public boolean EmailCheck(ArrayList<Email> list){
        int counter=0;
        if(list.size()>=2){
            Toast.makeText(this, "full", Toast.LENGTH_SHORT).show();
        }else if(list.size() >= 1) {
            for(int i=0;i<list.size();i++){
                if ((list.get(i).getType()).equals(Ntype.getSelectedItem().toString())){
                    Toast.makeText(this, "type already exist in address", Toast.LENGTH_SHORT).show();
                    return  false;
                }
                counter+=1;
            }
            if(counter==list.size())
            {
                return  true;
            }
        }
        return true;
    }

    /**
     * to insert new email id
     * @param id
     */
    public void insertNEmail(long id) {
        ArrayList<Email> EmailList = (ArrayList<Email>) db.dao().getEmail((int) id);
        if (EmailCheck(EmailList)) {
            if (checkFor.validateEmail(Nemail.getText().toString())) {
                db.dao().insertEmail(new Email(id, NtypeEmail.getSelectedItem().toString(), Nemail.getText().toString()));
            } else {
                Nemail.setText("");
                Toast.makeText(AddressBookStructureActivity.this, "follow the email convention1", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "full", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * to check the type of the field and to stop the duplicate types in phone number
     * @param list
     * @return bool
     */
    public boolean PhoneNoCheck(ArrayList<PhoneNumber> list){
        int counter=0;
        if(list.size()>=2){
            Toast.makeText(this, "full", Toast.LENGTH_SHORT).show();
        }else if(list.size() <= 1) {
            for(int i=0;i<list.size();i++){
                if ((list.get(i).getType()).equals(Ntype.getSelectedItem().toString())){
                    Toast.makeText(this, "type already exist in address", Toast.LENGTH_SHORT).show();
                    return  false;
                }
                counter+=1;
            }
            if(counter==list.size())
            {
                return  true;
            }
        }
        return true;
    }

    /**
     * to insert second phonenumber
     * @param id
     */
    public void insertNPhoneNo(long id) {
        ArrayList<PhoneNumber> phoneList = (ArrayList<PhoneNumber>) db.dao().getPhoneList((int)id);
        if (PhoneNoCheck(phoneList)) {
            if (checkFor.phoneNumberValidation(NPhoneNo.getText().toString())) {
                db.dao().insertPhone(new PhoneNumber(id, NtypePhoneNo.getSelectedItem().toString(), NPhoneNo.getText().toString()));
            }else{
                Toast.makeText(this, "wrong format", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "full", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * to clear all the fields in the layout and to set counters to zero
     * acts as a reset button
     */
    public void clean(){
    state.setText("");
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
        linearLayoutForAddress.setVisibility(View.INVISIBLE);
       // linearLayoutForAddress.removeAllViews();
        countAddress=0;countEmail=0;
        countPhoneNo=0;

    }

    /**
     * to set data data returned by gallery
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Nullable
    @Override
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

    /**
     * to notify the recyclerview
     */
    public void updateRecyclerView(){
        recyclerViewAdapter.userInfo.add(recyclerViewAdapter.userInfo.size(),new UserInfo(new UserName((int)idOfUser(),
                firstName.getText().toString(),lastName.getText().toString()),
                new Image()
                ,db.dao().getPhoneList((int)idOfUser()),db.dao().getEmail((int)idOfUser()),
                db.dao().getAddress((int)idOfUser())));

        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.userInfo.size()-1);
        //Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
    }

    }
