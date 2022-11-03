package com.example.addressbook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Address {
    @PrimaryKey(autoGenerate = true)
       public  int address_id;
    long id;
    @ColumnInfo
    String type;
    @ColumnInfo
    String line1;
    @ColumnInfo
    String line2;
    @ColumnInfo
    String city;
    @ColumnInfo
    String state;
    @ColumnInfo
    String country;
    @ColumnInfo
    String pincode;

    public Address(int address_id, long id, String type, String line1,
                   String line2, String city, String state, String country, String pincode) {
        this.address_id = address_id;
        this.id = id;
        this.type = type;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
    @Ignore
    public Address( long id, String type, String line1,
                   String line2, String city, String state, String country, String pincode) {

        this.id = id;
        this.type = type;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
