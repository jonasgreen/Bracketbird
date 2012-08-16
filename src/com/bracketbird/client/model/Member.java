package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.util.*;

import java.io.*;
import java.util.*;

/**
 *
 */
public class Member extends Model<MemberId> implements Serializable {
    private static final long serialVersionUID = 3110190178366969283L;

    private ClubId clubId;
    private UserId userId;

    private Integer memberType;

    private Date createdDate;

    private Date lastChangeDate;

    private String firstName;
    private String lastName;
    private String nickName;


    public Member() {
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

    public String getShortName() {
        return firstName + " " + lastName.charAt(0) + ".";
    }

    public boolean isAdmin() {
        return memberType == UserStateConstant.ADMIN.getValue();
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public void appendName(StringBuffer sb) {
        sb.append(getFirstName()).append(" ").append(getLastName());
    }

    public void appendLongName(StringBuffer sb){
        appendName(sb);
        if (!StringUtil.isEmpty(getNickName())) {
            sb.append(" - ").append(getNickName());
        }
    }

    public String getLongName() {
        if (StringUtil.isEmpty(getNickName())) {
            return getName();

        }
        return getName() + " - " +getNickName();
    }

}