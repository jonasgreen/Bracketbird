package com.bracketbird.client.model;


import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;

import java.io.*;

/**
 *
 */
public class CreateClub extends Creater implements Serializable {
    private static final long serialVersionUID = -8972293682760311154L;

    private String name;
    private String description;
    private Integer visibility;
    private Integer sportType;
    private Integer timeZoneOffset;
    private long id;

    private UserId userId;

    public CreateClub() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}