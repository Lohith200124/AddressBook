package com.example.addressbook.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Type {
    @PrimaryKey
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
