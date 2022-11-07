package com.example.addressbook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Image implements Serializable {
    @PrimaryKey
    int imageId;
    @ColumnInfo(defaultValue = "")
    String uri;
    @ColumnInfo
    long id;

    public Image(int imageId, String uri, long id) {
        this.imageId = imageId;
        this.uri = uri;
        this.id = id;
    }
    @Ignore
    public Image( String uri, long id) {

        this.uri = uri;
        this.id = id;
    }
    @Ignore
    public  Image(){}

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
