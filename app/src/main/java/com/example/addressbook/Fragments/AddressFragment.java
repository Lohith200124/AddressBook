package com.example.addressbook.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbook.Activity.ValidationClass;
import com.example.addressbook.Adapter.RecyclerViewAdapter;
import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.UserName;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/*
*
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
*/
public class AddressFragment extends Fragment {
    private EditText firstName,lastName,state,country,zipcode,line1,line2,city,Nstate,
            Ncountry,Nzipcode,Nline1,Nline2,Ncity;
    private Spinner type,Ntype;
    private Button add,next;
    private LinearLayout linearLayoutForAddress;
    int countAddress=0;
    TextInputLayout textInputLayout_line1,textInputLayout_line2,textInputLayout_state
            ,textInputLayout_country,textInputLayout_zipcode,textInputLayout_type,textInputLayout_city
            ,textInputLayout_firstName,textInputLayout_lastName,textInputLayout_new_zipcode;
    ValidationClass checkFor = new ValidationClass();
    DataBaseHelper db = DataBaseHelper.getDb(getActivity());
    int saveButtonCount=0;
    String item;
    RecyclerViewAdapter recyclerViewAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AddressFragment() {
        // Required empty public constructor
    }

/*    *
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddressFragment.
    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        linearLayoutForAddress = (LinearLayout) view.findViewById(R.id.AddressofUser);
      /*  *//*linearLayoutForEmail = getView().findViewById(R.id.EmailLayout);
        linearLayoutForPhone = getView().findViewById(R.id.phoneLinearLayout);*//*
        gallery = getView().findViewById(R.id.buttonGallery);
        picture = getView().findViewById(R.id.image1);*/
        next = view.findViewById(R.id.next);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        state = view.findViewById(R.id.state);
        country = view.findViewById(R.id.country);
        zipcode = view.findViewById(R.id.zipcode);
        type = view.findViewById(R.id.type);
        add = view.findViewById(R.id.add);
        city=view.findViewById(R.id.city);
        line1=view.findViewById(R.id.line1);
        line2 = view.findViewById(R.id.line2);
       /* typeEmail = getView().findViewById(R.id.type1);
        ArrayAdapter<CharSequence> adapterEmail=ArrayAdapter.createFromResource(this, R.array.type1, android.R.layout.simple_spinner_item);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typeEmail.setAdapter(adapterEmail);*/
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type.setAdapter(adapter);
        textInputLayout_line1 = view.findViewById(R.id.line1TextInputLayout);
        textInputLayout_line2=view.findViewById(R.id.line2TextInputLayout);
        textInputLayout_state = view.findViewById(R.id.stateTextInputLayout);
        textInputLayout_country=view.findViewById(R.id.countryTextInputLayout);
        textInputLayout_zipcode=view.findViewById(R.id.zipcodeTextInputLayout);
        textInputLayout_type=view.findViewById(R.id.spinnerTextInputLayout);
        textInputLayout_city = view.findViewById(R.id.cityTextInputLayout);
        textInputLayout_firstName = view.findViewById(R.id.firstNameTextInputLayout);
        textInputLayout_lastName = view.findViewById(R.id.lastNameTextInputLayout);
        textInputLayout_new_zipcode = view.findViewById(R.id.newZipcodeTextInputLayout);
       /* ArrayAdapter<CharSequence> adapterPhone=ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typePhoneNo.setAdapter(adapterPhone);*/
        Nline1= view.findViewById(R.id.newline1);
        Nline2 = view.findViewById(R.id.newline2);
        Ncity = view.findViewById(R.id.newcity);
        Nstate = view.findViewById(R.id.newstate);
        Ncountry = view.findViewById(R.id.newcountry);
        Nzipcode = view.findViewById(R.id.newzipcode);
        Ntype = (Spinner) view.findViewById(R.id.newtype);
       /* NtypeEmail = (Spinner)getView().findViewById(R.id.newtype1);
        NtypePhoneNo = (Spinner)getView().findViewById(R.id.newtype2);
        ArrayAdapter<CharSequence> NadapterEmail=ArrayAdapter.createFromResource(this, R.array.type1, android.R.layout.simple_spinner_item);
        NadapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NtypeEmail.setAdapter(NadapterEmail);*/
        ArrayAdapter<CharSequence> Nadapter=ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
        Nadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Ntype.setAdapter(Nadapter);

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
        next.setOnClickListener(new View.OnClickListener() {

            String line1Str,stateStr,countryStr,zipcodeStr,line2Str,cityStr,firstNameStr,lastNameStr;

            long id;
            @Override
            public void onClick(View view) {
                firstNameStr = firstName.getText().toString();
                lastNameStr = lastName.getText().toString();
                line1Str = line1.getText().toString();
                line2Str = line2.getText().toString();
                cityStr = city.getText().toString();
                stateStr = state.getText().toString();
                countryStr = country.getText().toString();
                zipcodeStr = zipcode.getText().toString();
                if (line1Str.equals("") || cityStr.equals("") || stateStr.equals("") ||
                        countryStr.equals("") || zipcodeStr.equals("")||firstNameStr.equals("") ||lastNameStr.equals(""))
                {
                    if(line1Str.equals("")){
                        textInputLayout_line1.setError("");
                        textInputLayout_line1.setBoxBackgroundColor(Color.RED);
                    }
                    if(cityStr.equals("")){
                        textInputLayout_city.setError("");
                        textInputLayout_city.setBoxBackgroundColor(Color.RED);
                    }
                    if(stateStr.equals("")){
                        textInputLayout_state.setError("");
                        textInputLayout_state.setBoxBackgroundColor(Color.RED);
                    }
                    if(countryStr.equals("") ){
                        textInputLayout_country.setError("");
                        textInputLayout_country.setBoxBackgroundColor(Color.RED);
                    }
                    if(zipcodeStr.equals("")){
                        textInputLayout_zipcode.setError("");
                        textInputLayout_zipcode.setBoxBackgroundColor(Color.RED);
                    }
                    if(firstNameStr.equals("")){
                        textInputLayout_firstName.setError("");
                        textInputLayout_firstName.setBoxBackgroundColor(Color.RED);
                    }
                    if(lastNameStr.equals("")){
                        textInputLayout_lastName.setError("");
                        textInputLayout_lastName.setBoxBackgroundColor(Color.RED);
                    }
                }
                else{
                    if(idCheck()){
                        ArrayList<Address> addressArrayList;
                        addressArrayList = (ArrayList<Address>) db.dao().getAddress((int) id);
                        if(countAddress>=1){
                            if (AddressCheck(addressArrayList)) {
                                if (checkFor.zipcodeValidation(zipcode.getText().toString())) {
                                    if (checkFor.zipcodeValidation(Nzipcode.getText().toString())) {
                                        if((type.getSelectedItem().toString()).equals(Ntype.getSelectedItem().toString())){
                                            Toast.makeText(getActivity(), "check type", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Bundle result = new Bundle();
                                            result.putString("FirstName", firstNameStr);
                                            result.putString("LastName", lastNameStr);
                                            result.putString("Line1", line1Str);
                                            result.putString("Line2", line2Str);
                                            result.putString("State",stateStr);
                                            result.putString("City", cityStr);
                                            result.putString("Country", countryStr);
                                            result.putString("Zipcode", zipcodeStr);
                                            result.putInt("CountAddress", countAddress+=1);
                                            result.putString("Spinner", type.getSelectedItem().toString());
                                            result.putString("NLine1", Nline1.getText().toString());
                                            result.putString("NLine2", Nline2.getText().toString());
                                            result.putString("NCity", Ncity.getText().toString());
                                            result.putString("NState",Nstate.getText().toString());
                                            result.putString("NCountry", Ncountry.getText().toString());
                                            result.putString("NZipcode", Nzipcode.getText().toString());
                                            result.putString("NSpinner", Ntype.getSelectedItem().toString());
                                            getParentFragmentManager().setFragmentResult("nameAndAddress", result);
                                            FragmentManager fm = getActivity().getSupportFragmentManager();
                                            FragmentTransaction ft = fm.beginTransaction();
                                            ft.replace(R.id.new_insertion_structure, new EmailAndPhoneNumberFragment());
                                            ft.addToBackStack(null);
                                            ft.commit();
                                        }

                                    }else {
                                        textInputLayout_new_zipcode.setError("");
                                        textInputLayout_new_zipcode.setBoxBackgroundColor(Color.RED);
                                        ;
                                    }

                                } else {
                                    textInputLayout_zipcode.setError("");
                                    textInputLayout_zipcode.setBoxBackgroundColor(Color.RED);
                                    ;
                                }

                            }  else{
                                textInputLayout_type = (TextInputLayout) type.getSelectedView();
                                textInputLayout_type.setError("");
                                textInputLayout_type.setBoxBackgroundColor(Color.RED);
                            }

                        }else{
                            if(checkFor.zipcodeValidation(zipcode.getText().toString()))
                            { Bundle result = new Bundle();
                                result.putString("FirstName", firstNameStr);
                                result.putString("LastName", lastNameStr);
                                result.putString("Line1", line1Str);
                                result.putString("Line2", line2Str);
                                result.putString("City", cityStr);
                                result.putString("State",stateStr);
                                result.putString("Country", countryStr);
                                result.putString("Zipcode", zipcodeStr);
                                result.putInt("CountAddress", countAddress);
                                result.putString("Spinner", type.getSelectedItem().toString());
                            /*if (AddressCheck(addressArrayList)) {

                                if (countAddress >= 1) {
                                    result.putString("NLine1", Nline1.getText().toString());
                                    result.putString("NLine2", Nline2.getText().toString());
                                    result.putString("NCity", Ncity.getText().toString());
                                    result.putString("NCountry", Ncountry.getText().toString());
                                    result.putString("NZipcode", Nzipcode.getText().toString());
                                    result.putString("NSpinner", Ntype.getTransitionName());
                                }*/
                                getParentFragmentManager().setFragmentResult("nameAndAddress", result);
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.new_insertion_structure, new EmailAndPhoneNumberFragment());
                                ft.addToBackStack(null);
                                ft.commit();
                            }
                            else
                            {
                                textInputLayout_zipcode.setError("");
                                textInputLayout_zipcode.setBoxBackgroundColor(Color.RED);

                            }
                        /*else{
                                textInputLayout_type = (TextInputLayout) type.getSelectedView();
                                textInputLayout_type.setError("");
                                textInputLayout_type.setBoxBackgroundColor(R.drawable.background_border);
                            }*/
                            //saveButtonCount += 1;

                        }
                    }else{
                        textInputLayout_firstName.setError("");
                        textInputLayout_lastName.setError("");
                        textInputLayout_firstName.setBoxBackgroundColor(Color.RED);
                        textInputLayout_lastName.setBoxBackgroundColor(Color.RED);
                    }
                    }
                    }});
        return view;
    }
    public boolean AddressCheck(ArrayList<Address> list) {
        int counter = 0;
        if (list.size() >= 2) {
            Toast.makeText(getActivity(), "full", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < list.size(); i++) {
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
    public boolean idCheck() {
        int localCounter = 0;
        ArrayList<UserName> list = (ArrayList<UserName>) db.dao().getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            if ((firstName.getText().toString()).equalsIgnoreCase(list.get(i).getFirstName())) {
                return false;
            }
            localCounter++;
        }
        if (localCounter == list.size()) {
            //id = db.dao().insertUser(new UserName(firstName.getText().toString(), lastName.getText().toString()));
            return true;
        }
        //list.removeAll();
        return true;
    }
}