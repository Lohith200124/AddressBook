package com.example.addressbook.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class SignUp {
    @PrimaryKey
    @NonNull
  public  String userName1;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "hero")
    String hero;
    @ColumnInfo
    String firstName;
    @ColumnInfo
    String lastName;

    public SignUp(@NonNull String userName1, String password, String hero, String firstName,String lastName) {
        this.userName1 = userName1;
        this.password = password;
        this.hero = hero;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Ignore
    public SignUp(@NonNull String userName1, String password) {
        this.userName1 = userName1;
        this.password = password;
    }
    @NonNull
    public String getUserName() {
        return userName1;
    }

    public void setUserName(@NonNull String userName) {
        this.userName1 = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
