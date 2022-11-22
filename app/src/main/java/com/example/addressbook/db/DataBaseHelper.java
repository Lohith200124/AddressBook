package com.example.addressbook.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.Entity.UserName;

/**
 * singleton class to access database
 */
@Database(entities = {SignUp.class, UserName.class, Address.class, Email.class, PhoneNumber.class, Image.class},version = 19,exportSchema = false)
public abstract class DataBaseHelper extends RoomDatabase {
    private static String DB_NAME = "AddressBook";
    private static DataBaseHelper instance;
    public static DataBaseHelper getDb(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,DataBaseHelper.class,DB_NAME).
                    fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
   public abstract AddressBookDao dao();
    public abstract  UserInfoDao userInfoDao();
}
