package com.bracketbird.server.jdo;

import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.JDO;

import javax.jdo.annotations.*;
import java.util.Date;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class WallMessageJDO extends JDO {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private Key clubKey;

    @Persistent
    private String userName;

    @Persistent
    private String message;

    @Persistent
    private Integer type;

    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public WallMessageJDO() {
    }


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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

    public Key getClubKey() {
        return clubKey;
    }

    public void setClubKey(Key clubKey) {
        this.clubKey = clubKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}