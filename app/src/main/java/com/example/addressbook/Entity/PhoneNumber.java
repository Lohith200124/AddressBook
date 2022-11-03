package com.example.addressbook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class PhoneNumber {
    @PrimaryKey(autoGenerate = true)
    int phoneNoId;
    long id;
    @ColumnInfo
    String type;
    @ColumnInfo
    String phonNo;

    public PhoneNumber(int phoneNoId, long id, String type, String phonNo) {
        this.phoneNoId = phoneNoId;
        this.id = id;
        this.type = type;
        this.phonNo = phonNo;
    }

    @Ignore
    public PhoneNumber(long id,String type, String phonNo) {
        this.id=id;
        this.type = type;
        this.phonNo = phonNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhonNo() {
        return phonNo;
    }

    public void setPhonNo(String phonNo) {
        phonNo = phonNo;
    }

    public int getPhoneNoId() {
        return phoneNoId;
    }

    public void setPhoneNoId(int phoneNoId) {
        this.phoneNoId = phoneNoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
