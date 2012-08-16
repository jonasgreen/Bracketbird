package com.bracketbird.server.jdo;

import com.bracketbird.server.jdo.club.TournamentBrickJDO;
import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.JDO;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class UserJDO extends JDO {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private com.google.appengine.api.datastore.Key key;

    //name things
    @Persistent
    private String firstName;

    @Persistent
    private String lastName;

    @Persistent
    private String nickName;

    //address
    @Persistent
    private String country;

    @Persistent
    private String city;

    @Persistent
    private String cityCode;

    @Persistent
    private String street;

    @Persistent
    private Boolean deleted;

    //contatct
    @Persistent
    private String email;

    @Persistent
    private String phone;


    @Persistent
    private String password;

    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public UserJDO() {
    }


    public com.google.appengine.api.datastore.Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
