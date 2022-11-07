package com.example.addressbook.db;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

/**
 * dao for user info
 */
@Dao
public interface UserInfoDao {
    @Transaction
@Query("select * from UserName")
    public List<UserInfo> getUserInfo();
}
