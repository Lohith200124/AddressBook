package com.example.addressbook.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.Entity.UserName;

import java.util.ArrayList;
import java.util.List;

@Dao
public  interface AddressBookDao {
@Insert
    public void insert(SignUp signUp);
@Delete
    public void delete(SignUp signUp);
@Update
    public void update(SignUp signUp);
@Query("select * from SignUp")
    public List<SignUp> getAllUSers();

    /**
     * @param userName
     * @return
     */
@Insert
    public long insertUser(UserName userName);
@Insert
    public void insertAddress(Address address);
@Insert
    public void insertEmail(Email email);
@Insert
    public void insertPhone(PhoneNumber phoneNumber);
@Query("select id from username where firstName = :str ")
 public int getId(String str);
@Query("select * from PhoneNumber where id=:id")
    public List<UserNamePhoneNo> getPhone(int id);
@Query("select * from Address where id=:id")
    public List<Address> getAddress(int id);
@Query("select * from Email where id=:id")
    public List<Email> getEmail(int id);
@Query("select * from PhoneNumber")
    public List<UserInfo> getUserInfo();
@Query("delete  from phoneNumber where id =4")
    public void delPhone();
    @Query("delete  from Email where id =4")
    public void delEmail();
    @Query("delete  from Address where id =4")
    public void delAddress();
}
