package com.bracketbird.client.model;


import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;

import java.io.*;
import java.util.*;

/**
 *
 */
public class User extends Model<UserId> implements Serializable {
    private static final long serialVersionUID = 4834656240567205753L;



    private Boolean deleted;

    //name things
    private String firstName;

    private String lastName;

    private String nickName;


    //address
    private Address address;


    //contatct
    private String email;

    private String phone;


    private String password;

    private Date createdDate;

    private Date lastChangeDate;


    public User() {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getName(){
        return getFirstName() + " " + getLastName();
    }

    public String getNationality(){
        if(address != null){
            return address.getNationalCode();
        }
        return null;
    }

}
