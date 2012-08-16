package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;

import java.io.*;


/**
 *
 */
public class CreateMember extends Creater implements Serializable {
    private static final long serialVersionUID = 7649302654454778656L;

    private ClubId clubId;
    private UserId userId;

    //redundant to optimize search for members
    private String firstName;
    private String lastName;
    private String nickName;
    private String phone;
    private String email;

    private Integer memberType;

    public CreateMember() {
    }

    public ClubId getClubId() {
        return clubId;
    }

    public void setClubId(ClubId clubId) {
        this.clubId = clubId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
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
}