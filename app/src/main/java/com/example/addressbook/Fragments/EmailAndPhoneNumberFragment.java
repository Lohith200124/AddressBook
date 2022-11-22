package com.example.addressbook.Fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.addressbook.Activity.AddressBookStructureActivity;
import com.example.addressbook.Activity.HomePageActivity;
import com.example.addressbook.Activity.ValidationClass;
import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class EmailAndPhoneNumberFragment extends Fragment {

    /*// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/
    String first_name, last_name, line_1, line_2, city_str, state_str, country_str, zipcode_str, type,
            Ntype = "office", n_line_1, n_line_2, n_city_str, n_state_str, n_country_str, n_zipcode_str, n_type;
    private EditText email, phoneNumber;
    private Spinner type_Email, type_PhoneNumber;
    private Button Add_Email, Add_PhoneNumber, gallery, save, cancel;
    private EditText newEmail, newPhoneNumber;
    private Spinner new_type_email, New_type_PhoneNumber;
    private ImageView picture;
    private LinearLayout linearLayout_For_Email, linearLayout_For_Phone;
    int countEmail = 0, countPhoneNo = 0,countAddress=0;
    private final static int GALLERY_REQ_CODE = 150;
    ValidationClass checkFor = new ValidationClass();
    DataBaseHelper db = DataBaseHelper.getDb(getActivity());
    TextInputLayout email_textInputLayout,new_email_textInputLayout,
            phoneNumber_textInputLayout,new_phoneNumber_textInputLayout;
    int saveButtonCount = 0;
    String item;
    RecyclerViewAdapter recyclerViewAdapter;
    Uri uri_image;
    ActivityResultLauncher<String> requestPermissionLauncher;
    ActivityResultLauncher<String> activityResultLauncher;
    public EmailAndPhoneNumberFragment() {
        // Required empty public constructor
    }

    /*  public static EmailAndPhoneNumberFragment newInstance(String param1, String param2) {
          EmailAndPhoneNumberFragment fragment = new EmailAndPhoneNumberFragment();
          Bundle args = new Bundle();
          args.putString(ARG_PARAM1, param1);
          args.putString(ARG_PARAM2, param2);
          fragment.setArguments(args);
          return fragment;
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          if (getArguments() != null) {
              mParam1 = getArguments().getString(ARG_PARAM1);
              mParam2 = getArguments().getString(ARG_PARAM2);
          }
      }
  */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_and_phone_number, container, false);
        save = view.findViewById(R.id.saveButton);
        cancel = view.findViewById(R.id.cancelButton);
        linearLayout_For_Email = view.findViewById(R.id.EmailLayout);
        linearLayout_For_Phone = view.findViewById(R.id.phoneLinearLayout);
        gallery = view.findViewById(R.id.buttonGallery);
        picture = view.findViewById(R.id.image1);
        email = view.findViewById(R.id.emailAddress);
        phoneNumber_textInputLayout=view.findViewById(R.id.textInputEditTextPhoneNumber);
        phoneNumber = view.findViewById(R.id.phoneNo);
        type_PhoneNumber = view.findViewById(R.id.type2);
        Add_PhoneNumber = view.findViewById(R.id.addPhone);
        Add_Email = view.findViewById(R.id.addEmail);
        type_Email = view.findViewById(R.id.type1);
        ArrayAdapter<CharSequence> adapterEmail = ArrayAdapter.createFromResource(getActivity(), R.array.type1, android.R.layout.simple_spinner_item);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type_Email.setAdapter(adapterEmail);
        ArrayAdapter<CharSequence> adapterPhone = ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
        adapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type_PhoneNumber.setAdapter(adapterPhone);
        newEmail = view.findViewById(R.id.newemailAddress);
        newPhoneNumber = view.findViewById(R.id.newphoneNo);
        new_type_email = (Spinner) view.findViewById(R.id.newtype1);
        New_type_PhoneNumber = (Spinner) view.findViewById(R.id.newtype2);
        ArrayAdapter<CharSequence> NadapterEmail = ArrayAdapter.createFromResource(getActivity(), R.array.type1, android.R.layout.simple_spinner_item);
        NadapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        new_type_email.setAdapter(NadapterEmail);
        ArrayAdapter<CharSequence> NadapterPhone = ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
        NadapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        New_type_PhoneNumber.setAdapter(NadapterPhone);
        getParentFragmentManager().setFragmentResultListener("nameAndAddress", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                first_name = result.getString("FirstName");
                last_name = result.getString("LastName");
                line_1 = result.getString("Line1");
                line_2 = result.getString("Line2");
                city_str = result.getString("City");
                state_str = result.getString("State");
                country_str = result.getString("Country");
                zipcode_str = result.getString("Zipcode");
                countAddress = result.getInt("CountAddress");
                type = result.getString("Spinner");
                n_line_1 = result.getString("NLine1");
                n_line_2 = result.getString("NLine2");
                n_city_str = result.getString("NCity");
                n_state_str = result.getString("NState");
                n_country_str = result.getString("NCountry");
                n_zipcode_str = result.getString("NZipcode");
                n_type = result.getString("NSpinner");
            }
        });
        Add_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countEmail += 1;
                linearLayout_For_Email.setVisibility(View.VISIBLE);
            }
        });
        Add_PhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countPhoneNo += 1;
                linearLayout_For_Phone.setVisibility(View.VISIBLE);
            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                picture.setImageURI(result);
                uri_image = result;

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityResultLauncher.launch("application/*");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            long id;
            @Override
            public void onClick(View view) {
                if((email.getText().toString()).equals("")||(phoneNumber.getText().toString()).equals("")){
                        email_textInputLayout.setError("");
                        email_textInputLayout.setBoxBackgroundColor(Color.RED);
                        phoneNumber_textInputLayout.setError("");
                        phoneNumber_textInputLayout.setBoxBackgroundColor(Color.RED);
                }else{
                    if(countAddress == 0 && countEmail == 0 && countPhoneNo == 0){
                        insertBasic();
                    }
                    else if(countAddress >= 1 && countEmail >= 1 && countPhoneNo >= 1){
                        if((type).equals(Ntype) &&(type_Email.getSelectedItem().toString()).
                                        equals(new_type_email.getSelectedItem().toString()) &&
                                (type_PhoneNumber.getSelectedItem().toString()).
                                        equals(New_type_PhoneNumber.getSelectedItem().toString())||(type_PhoneNumber.getSelectedItem().toString()).
                                equals(New_type_PhoneNumber.getSelectedItem().toString()))
                        {
                            if((type_Email.getSelectedItem().toString()).
                                    equals(new_type_email.getSelectedItem().toString()))
                            {
                                Toast.makeText(getActivity(), "email types are same", Toast.LENGTH_SHORT).show();
                            }
                            else if( (type_PhoneNumber.getSelectedItem().toString()).
                                    equals(New_type_PhoneNumber.getSelectedItem().toString())){
                                Toast.makeText(getActivity(), "phonenumber types are same", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            id = idOfUser();
                            insertBasic();
                            insertNAddress(id);
                            insertNEmail(id);
                            insertNPhoneNo(id);
                        }
                    }else if (countAddress >= 1 && countEmail == 0 && countPhoneNo == 0) {
                        if ((type).equals(Ntype)) {
                            Toast.makeText(getActivity(), "check the type two", Toast.LENGTH_SHORT).show();
                        } else {
                                id = idOfUser();
                                insertBasic();
                                insertNAddress(id);
                        }
                    } else if (countAddress == 0 && countEmail >= 1 && countPhoneNo == 0) {
                        if ((type_Email.getSelectedItem().toString()).
                                equals(new_type_email.getSelectedItem().toString())) {

                            Toast.makeText(getActivity(), "check the type three", Toast.LENGTH_SHORT).show();
                        } else {
                                id = idOfUser();
                                insertBasic();
                                insertNEmail(id);
                        }
                    } else if (countAddress == 0 && countEmail == 0 && countPhoneNo >= 1) {
                        if ((type_PhoneNumber.getSelectedItem().toString()).
                                equals(New_type_PhoneNumber.getSelectedItem().toString())) {
                            Toast.makeText(getActivity(), "check the type four", Toast.LENGTH_SHORT).show();

                        } else {
                                id = idOfUser();
                                insertBasic();
                                insertNPhoneNo(id);
                        }
                    } else if (countAddress >= 1 && countEmail >= 1 && countPhoneNo == 0) {
                        if ((type).equals(Ntype) &&(type_Email.getSelectedItem().toString()).
                                equals(new_type_email.getSelectedItem().toString())) {
                            Toast.makeText(getActivity(), "check the type five", Toast.LENGTH_SHORT).show();
                        } else {
                                id = idOfUser();
                                insertBasic();
                                insertNAddress(id);
                                insertNEmail(id);
                        }
                    } else if (countAddress == 0 && countEmail >= 1 && countPhoneNo >= 1) {
                        if ((type_Email.getSelectedItem().toString()).
                                equals(new_type_email.getSelectedItem().toString()) &&
                                (type_PhoneNumber.getSelectedItem().toString()).
                                        equals(New_type_PhoneNumber.getSelectedItem().toString())) {
                            Toast.makeText(getActivity(), "check the type six", Toast.LENGTH_SHORT).show();
                        } else {
                                id = idOfUser();
                                insertBasic();
                                insertNEmail(id);
                                insertNPhoneNo(id);
                        }
                    } else if (countAddress >= 1 && countEmail == 0 && countPhoneNo >= 1) {
                        if ((type).equals(Ntype)  &&
                                (type_PhoneNumber.getSelectedItem().toString()).
                                        equals(New_type_PhoneNumber.getSelectedItem().toString())) {
                            Toast.makeText(getActivity(), "check the type seven", Toast.LENGTH_SHORT).show();
                        } else {
                                id = idOfUser();
                                insertBasic();
                                insertNAddress(id);
                                insertNPhoneNo(id);
                        }
                    }
                    Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    startActivity(intent);
                }
           }
        });
        /**
         * to insert single user
         */
        return view;
    }

    public void insertBasic() {
        String emailStr, phonNoStr;
        List<UserName> list;
        long id = 0;
        ArrayList<Address> addressArrayList;
        ArrayList<Email> EmailList;
        ArrayList<PhoneNumber> phoneList;
        emailStr = email.getText().toString();
        phonNoStr = phoneNumber.getText().toString();
            if (checkFor.phoneNumberValidation(phonNoStr)) {
                if (checkFor.validateEmail(emailStr)) {

                    id = idOfUser();
                    addressArrayList = (ArrayList<Address>) db.dao().getAddress((int) id);
                    if(AddressCheck(addressArrayList)){
                        db.dao().insertAddress(new Address(id, type, line_1, line_2,
                                city_str, state_str, country_str, zipcode_str));
                    }

                    EmailList = (ArrayList<Email>) db.dao().getEmail((int) id);
                    if (EmailCheck(EmailList)) {
                    db.dao().insertEmail(new Email(id, type_Email.getSelectedItem().toString(), emailStr));
                      }
                    phoneList = (ArrayList<PhoneNumber>) db.dao().getPhoneList((int) id);
                    if (PhoneNoCheck(phoneList)) {
                    db.dao().insertPhone(new PhoneNumber(id, type_PhoneNumber.getSelectedItem().toString(), phonNoStr));
                     }
                    try {
                        db.dao().insertImage(new Image(uri_image.toString(), idOfUser()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    email.setText("");
                    email_textInputLayout.setError("");
                    email_textInputLayout.setBoxBackgroundColor(Color.RED);
                }
            } else {
                phoneNumber.setText("");
                phoneNumber_textInputLayout.setError("");
                phoneNumber_textInputLayout.setBoxBackgroundColor(Color.RED);
            }

    }

    public long idOfUser() {
        long id;
        int localCounter = 0;
        ArrayList<UserName> list = (ArrayList<UserName>) db.dao().getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            if ((first_name).equals(list.get(i).getFirstName())) {
                id = db.dao().getId(first_name);
                return id;
            }
            localCounter++;
        }
        if (localCounter == list.size()) {
        id = db.dao().insertUser(new UserName(first_name, last_name));
        return id;
        }
        //list.removeAll();
        return 0;
    }

    public boolean AddressCheck(ArrayList<Address> list) {
        int counter = 0;
        if (list.size() >= 2) {
            Toast.makeText(getActivity(), "full", Toast.LENGTH_SHORT).show();
        } else if(list.size()<=1){
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getType()).equals(Ntype)) {
                    Toast.makeText(getActivity(), "type already exist in address", Toast.LENGTH_SHORT).show();
                    return false;
                }
                counter += 1;
            }
            if (counter == list.size()) {
                return true;
            }
        }
        return true;
    }

    public boolean EmailCheck(ArrayList<Email> list) {
        int counter = 0;
        if (list.size() >= 2) {
            Toast.makeText(getActivity(), "full", Toast.LENGTH_SHORT).show();
        } else if (list.size() <= 1) {
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getType()).equals(new_type_email.getSelectedItem().toString())) {
                    Toast.makeText(getActivity(), "type already exist in address", Toast.LENGTH_SHORT).show();
                    return false;
                }
                counter += 1;
            }
            if (counter == list.size()) {
                return true;
            }
        }
        return true;
    }

    public boolean PhoneNoCheck(ArrayList<PhoneNumber> list) {
        int counter = 0;
        if (list.size() >= 2) {
            Toast.makeText(getActivity(), "full", Toast.LENGTH_SHORT).show();
        } else if (list.size() <= 1) {
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getType()).equals(New_type_PhoneNumber.getSelectedItem().toString())) {
                    Toast.makeText(getActivity(), "type already exist in address", Toast.LENGTH_SHORT).show();
                    return false;
                }
                counter += 1;
            }
            if (counter == list.size()) {
                return true;
            }
        }
        return true;
    }


   /* */
/*    @Nullable
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str;
        Uri uri;
        int count=0;
        if(resultCode == RESULT_OK ){
            if(requestCode == GALLERY_REQ_CODE ){
                uri =  data.getData();
                str = uri.toString();
                try {
                    db.dao().insertImage(new Image(str, idOfUser()));
                    count+=1;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if(count>=1){
                    picture.setImageURI(data.getData());
                }
            }
        }
    }*/
    public void insertNAddress(long id){
            db.dao().insertAddress(new Address(id, n_type, n_line_1, n_line_2,
                    n_city_str, n_country_str, n_country_str, n_zipcode_str));
    }
    public void insertNEmail(long id) {
        ArrayList<Email> EmailList = (ArrayList<Email>) db.dao().getEmail((int) id);
        if (EmailCheck(EmailList)) {
            if (checkFor.validateEmail(newEmail.getText().toString())) {
                db.dao().insertEmail(new Email(id, new_type_email.getSelectedItem().toString(), newEmail.getText().toString()));
            } else {
                newEmail.setText("");
                new_email_textInputLayout.setError("follow email convention");
                new_email_textInputLayout.setBoxBackgroundColor(Color.RED);
            }
        }
        else{
            new_email_textInputLayout.setError("check the type");
            new_email_textInputLayout.setBoxBackgroundColor(Color.RED);
        }
    }
    public void insertNPhoneNo(long id) {
        ArrayList<PhoneNumber> phoneList = (ArrayList<PhoneNumber>) db.dao().getPhoneList((int)id);
        if (PhoneNoCheck(phoneList)) {
            if (checkFor.phoneNumberValidation(newPhoneNumber.getText().toString())) {
                db.dao().insertPhone(new PhoneNumber(id, New_type_PhoneNumber.getSelectedItem().toString(), newPhoneNumber.getText().toString()));
            }else{
               new_phoneNumber_textInputLayout.setError("format of phonenumber");
               new_phoneNumber_textInputLayout.setBoxBackgroundColor(Color.RED);
            }
        }
        else{
            Toast.makeText(getActivity(), "full", Toast.LENGTH_SHORT).show();
        }
    }

}



