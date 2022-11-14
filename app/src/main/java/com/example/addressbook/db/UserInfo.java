package com.example.addressbook.db;

import android.os.Build;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.UserName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

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


   /* @Override
    public int compare(Object o, Object t1) {
        return 0;
    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Comparator thenComparing(Comparator other) {
        return Comparator.super.thenComparing(other);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }*/
}
