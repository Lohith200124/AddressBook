package com.example.addressbook.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.Entity.SignUp;
import com.example.addressbook.Entity.UserName;

import java.util.ArrayList;
import java.util.List;

/**
 * main dao interface for databse addressbook
 */
@Dao
public  interface AddressBookDao {
    /**
     * for signup insertion
     * @param signUp
     */
    @Insert
    public void insert(SignUp signUp);

    /**
     * for signup deltion
     * @param signUp
     */
    @Delete
    public void delete(SignUp signUp);

    /**
     * to update the signup
     * @param signUp
     */
    @Update
    public void update(SignUp signUp);

    /**
     * to update name
     * @param userName
     */
    @Update
public void updateName(UserName userName);

    /**
     * fetch all users
     * @return
     */
    @Query("select * from SignUp")
    public List<SignUp> getAllUSers();

    /**insert user
     * @param userName
     * @return long
     */
@Insert
    public long insertUser(UserName userName);

    /**
     * insert address
     * @param address
     */
    @Insert
    public void insertAddress(Address address);

    /**
     * insert email
     * @param email
     */
    @Insert
    public void insertEmail(Email email);

    /**
     *
     * @param phoneNumber
     */
    @Insert
    public void insertPhone(PhoneNumber phoneNumber);

    /**
     *
     * @param image
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
public void insertImage(Image image);

    /**
     *
     * @param email
     */
    @Update
public void updateEmail(Email email);

    /**
     *
     * @param phoneNumber
     */
    @Update
public void updatePhoneNo(PhoneNumber phoneNumber);

    /**
     *
     * @param id
     * @param str
     * @return
     */
    @Query("select emailId from email where id=:id and type=:str")
public int getEmailId(long id,String str);

    /**
     *
     * @param id
     * @param str
     * @return
     */
    @Query("select address_id from Address where id=:id and type=:str")
public int getAddressId(long id,String str);

    /**
     *
     * @param id
     * @param str
     * @return
     */
    @Query("select phoneNoId from PhoneNumber where id=:id and type=:str")
public int getPhonenoId(long id,String str);

    /**
     *
     * @param id
     * @return
     */
    @Query("select uri from Image where id=:id")
public String getImageUri(int id);

    /**
     *
     * @param str
     * @return
     */
    @Query("select id from username where firstName = :str ")
 public int getId(String str);

    /**
     *
     * @param id
     * @return
     */
    @Transaction
    @Query("select * from PhoneNumber where id=:id")
    public List<UserNamePhoneNo> getPhone(int id);

    /**
     *
     * @param id
     * @return
     */
    @Query("select * from PhoneNumber where id=:id")
    public List<PhoneNumber> getPhoneList(int id);

    /**
     *
     * @param id
     * @return
     */
    @Query("select * from Address where id=:id")
    public List<Address> getAddress(int id);

    /**
     *
     * @param id
     * @return
     */
    @Query("select * from Email where id=:id")
    public List<Email> getEmail(int id);

    /**
     *
     * @param id
     * @return
     */
    @Transaction
    @Query("select * from UserName where id=:id")
    public UserInfo getUserInfoObj(int id);

    /**
     *
     * @param str
     * @return
     */
    @Query("select password from SignUp where userName1=:str")
public String getPassword(String str);

    /**
     * delete query for phone number to remove tuple
     */
    @Query("delete  from phoneNumber ")
    public void delPhone();

    /**
     * delete query for Email to remove tuple
     */
    @Query("delete  from Email ")
    public void delEmail();

    /**
     * delete query for userName to remove tuple
     */
    @Query("delete  from UserName")
    public void delUserName1();

    /**
     * delete query for Image to remove tuple
     */
    @Query("delete  from Image")
    public void delImage();

    /**
     * delete query forAddress to remove tuple
     */
    @Query("delete  from Address")
    public void delAddress();

    /**
     *
     * @return
     */
    @Query("select * from username")
    public List<UserName> getAllUsers();

    /**
     *
     * @param id
     */
    @Query("delete  from PhoneNumber where id=:id")
    public void delPhoneById(int id);

    /**
     *
     * @param id
     */
    @Query("delete  from Email where id=:id")
    public void delEmailbyId(int id);

    /**
     *
     * @param id
     */
    @Query("delete  from UserName  where id=:id")
    public void delUserName1ById(int id);

    /**
     *
     * @param id
     */
    @Query("delete  from Image  where id=:id")
    public void delImageById(int id);

    /**
     *
      * @param id
     */
    @Query("delete  from Address  where id=:id")
    public void delAddressById(int id);

    /**
     *
     * @param address
     */
    @Update
    public void updateAddress(Address address);
}
