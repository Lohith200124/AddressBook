package com.example.addressbook.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserName implements Serializable {
@PrimaryKey(autoGenerate = true)
int id;
@ColumnInfo
@NonNull
    String firstName;
@ColumnInfo
    String lastName;

    public UserName(int id, @NonNull String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Ignore
    public UserName(@NonNull String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Ignore
    public UserName(int id,@NonNull String lastName) {
        this.id = id;
        this.lastName = lastName;
    }



@Ignore
public  UserName(){}
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
