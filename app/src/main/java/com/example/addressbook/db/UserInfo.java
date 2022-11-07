package com.example.addressbook.db;

import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;

import java.io.Serializable;
import java.util.List;

/**
 * a class consist of all relations between user and address,email,phone and image
 */
public class UserInfo implements Serializable {
    @Embedded
    UserName userName;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Image.class)
    Image image;
    @Relation(parentColumn = "id",entityColumn = "id",entity = PhoneNumber.class)
    List<PhoneNumber> phoneList;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Email.class)
    List<Email> emailList;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Address.class)
    List<Address> list ;


    public UserInfo(UserName userName, Image image, List<PhoneNumber> phoneList, List<Email> emailList, List<Address> list) {
        this.userName = userName;
        this.image = image;
        this.phoneList = phoneList;
        this.emailList = emailList;
        this.list = list;
    }

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
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
