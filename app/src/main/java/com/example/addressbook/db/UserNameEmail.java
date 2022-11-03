package com.example.addressbook.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.UserName;

import java.util.List;

public class UserNameEmail {
    @Embedded
    UserName username;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Email.class)
    List<Email> list;

    public UserName getUsername() {
        return username;
    }

    public void setUsername(UserName username) {
        this.username = username;
    }

    public List<Email> getList() {
        return list;
    }

    public void setList(List<Email> list) {
        this.list = list;
    }
}
