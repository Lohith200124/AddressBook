package com.example.addressbook.db;

import android.content.Intent;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;

import java.util.List;

/**
 * one-many for username and phone no and email
 */
public class UserNamePhoneNo {
    @Embedded
    UserName userName;
    @Relation(parentColumn = "id",entityColumn = "id",entity = PhoneNumber.class)
    List<PhoneNumber> phoneList;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Email.class)
    List<Email> emailList;

    public List<PhoneNumber> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<PhoneNumber> phoneList) {
        this.phoneList = phoneList;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }


    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }


}
