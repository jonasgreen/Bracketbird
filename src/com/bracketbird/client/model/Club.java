package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

import java.io.*;
import java.util.*;

/**
 *
 */
public class Club extends Model<ClubId> implements Serializable {
    private static final long serialVersionUID = 8110524168748387183L;

    private String name;

    private String nameLowerCase;

    private Integer sportType;

    private String description;

    private String nationality;

    private Integer visibility;

    private Integer timeZoneOffset;

    private Date createdDate;

    private Date lastChangeDate;

    public Club() {
        super();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNameLowerCase() {
        return nameLowerCase;
    }

    public void setNameLowerCase(String nameLowerCase) {
        this.nameLowerCase = nameLowerCase;
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

    public static List<Club> extractBySport(Collection<Club> clubs, Integer sType){
        if(clubs == null){
            return null;
        }
        List<Club> retVal = new ArrayList<Club>();
        for (Club club : clubs) {
            if(club.getSportType() == sType.intValue()){
                retVal.add(club);
            }
        }
        return retVal;
        
    }
}