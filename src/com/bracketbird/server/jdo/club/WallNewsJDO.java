package com.bracketbird.server.jdo.club;

import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.JDO;

import javax.jdo.annotations.*;
import java.util.Date;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class WallNewsJDO extends JDO {

	
	

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    //foreign key
    @Persistent
    private Key clubKey;

    @Persistent
    private String message;

    @Persistent
    private String title;

    @Persistent
    private Date invalidDate;

    @Persistent
    private Integer stayOnWall;


    @Persistent
    private Integer timeZoneOffset;


    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;

    @Persistent
    private String createdBy;


    public WallNewsJDO() {

    }


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getClubKey() {
        return clubKey;
    }

    public void setClubKey(Key clubKey) {
        this.clubKey = clubKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getStayOnWall() {
        return stayOnWall;
    }

    public void setStayOnWall(Integer stayOnWall) {
        this.stayOnWall = stayOnWall;
    }
}