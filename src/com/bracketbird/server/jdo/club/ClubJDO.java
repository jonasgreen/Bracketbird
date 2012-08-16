package com.bracketbird.server.jdo.club;

import com.bracketbird.server.dao.JDO;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.*;
import java.util.Date;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class ClubJDO extends JDO {

	

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;


    @Persistent
    private String name;


    @Persistent
    private Boolean personal;


    @Persistent
    private String nameLowercase;

    @Persistent
    private Integer sportType;

    @Persistent
    private String nationality;

    @Persistent
    private Integer timeZoneOffset;


    @Persistent
    private Integer visibility;

    @Persistent
    private String description;


    @Persistent
    private Boolean deleted;

    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public ClubJDO() {

    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameLowercase() {
        return nameLowercase;
    }

    public void setNameLowercase(String nameLowercase) {
        this.nameLowercase = nameLowercase;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getSportType() {
        return sportType;
    }

    public void setSportType(Integer sportType) {
        this.sportType = sportType;
    }

    public Integer getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(Integer timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    public Boolean getPersonal() {
        return personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }
}