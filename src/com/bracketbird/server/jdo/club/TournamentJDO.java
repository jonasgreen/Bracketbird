package com.bracketbird.server.jdo.club;

import com.bracketbird.server.dao.*;

import javax.jdo.annotations.*;
import java.util.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class TournamentJDO extends JDO {


    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private com.google.appengine.api.datastore.Key key;

    //foreign key
    @Persistent
    private com.google.appengine.api.datastore.Key tournamentChannelKey;


    @Persistent
    private String name;

    @Persistent
    private String password;

    @Persistent
    private String url;

    @Persistent
    private String viewUrl;


    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public TournamentJDO() {

    }

    public com.google.appengine.api.datastore.Key getKey() {
        return key;
    }

    public void setKey(com.google.appengine.api.datastore.Key key) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public com.google.appengine.api.datastore.Key getTournamentChannelKey() {
        return tournamentChannelKey;
    }

    public void setTournamentChannelKey(com.google.appengine.api.datastore.Key tournamentChannelKey) {
        this.tournamentChannelKey = tournamentChannelKey;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }
}