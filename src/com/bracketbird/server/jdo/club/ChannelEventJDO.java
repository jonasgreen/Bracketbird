package com.bracketbird.server.jdo.club;

import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.*;

import javax.jdo.annotations.*;
import java.util.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class ChannelEventJDO extends JDO {


    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    //foreign key
    @Persistent
    private Key tournamentKey;

    //a reference to the tournamentVO, tournamentLevelVO, playingTeamVO or matchVO.
    @Persistent
    private Key modelKey;

    @Persistent
    private Long id;

    @Persistent
    private String eventName;

    @Persistent
    private String value;
    
    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;







    public ChannelEventJDO() {

    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getTournamentKey() {
        return tournamentKey;
    }

    public void setTournamentKey(Key tournamentKey) {
        this.tournamentKey = tournamentKey;
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

    public Key getModelKey() {
        return modelKey;
    }

    public void setModelKey(Key modelKey) {
        this.modelKey = modelKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}