package com.example.addressbook.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.UserName;

import java.util.List;

public class UserNameAddress {
    @Embedded
    UserName userName;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Address.class)
    List<Address> list ;

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public List<Address> getList() {
        return list;
    }

    public void setList(List<Address> list) {
        this.list = list;
    }
}
