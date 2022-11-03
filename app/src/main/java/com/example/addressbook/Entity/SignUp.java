package com.example.addressbook.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SignUp {
    @PrimaryKey
    @NonNull
    String userName;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "hero")
    String hero;

    public SignUp(@NonNull String userName, String password, String hero) {
        this.userName = userName;
        this.password = password;
        this.hero = hero;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
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
}
