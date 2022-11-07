package com.example.addressbook.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.UserName;
/**
 * one-one relation between username and images
 */

public class UserNameImages {
    @Embedded
    UserName userName;
    @Relation(parentColumn = "id",entityColumn = "id",entity = Image.class)
    Image image;

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
