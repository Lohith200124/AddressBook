package com.example.addressbook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Email implements Serializable {
    @PrimaryKey(autoGenerate = true)
        int emailId;
    long id;
    @ColumnInfo
    String type;
    @ColumnInfo
    String email;

    public Email(int emailId, long id, String type, String email) {
        this.emailId = emailId;
        this.id = id;
        this.type = type;
        this.email = email;
    }
    @Ignore
    public Email(long id, String type, String email) {
        this.id = id;
        this.type = type;
        this.email = email;
    }


    public String getType() {

        return type;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }
}
