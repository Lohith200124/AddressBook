package com.example.addressbook.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;

import java.util.List;

public class UserInfo {
    @Embedded
    UserName userName;
    @Relation(parentColumn = "id",entityColumn = "id",entity = PhoneNumber.class)
    List<PhoneNumber> phoneList;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Email.class)
    List<Email> emailList;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Address.class)
    List<Address> list ;

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

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

    public List<Address> getList() {
        return list;
    }

    public void setList(List<Address> list) {
        this.list = list;
    }
}
