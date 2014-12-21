package com.bracketbird.server.jdo;

import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.server.dao.*;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.*;
import java.util.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class RTCEventJDO extends JDO {


    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    //foreign key
    @Persistent
    private Key tournamentKey;

    @Persistent
    private Long id;

    private Long stateId;

    @Persistent(serialized = "true", defaultFetchGroup = "true")
    private REvent event;

     @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;

    public RTCEventJDO() {

    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    @Override
    public Date getLastChangeDate() {
        return lastChangeDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public REvent getEvent() {
        return event;
    }

    public void setEvent(REvent event) {
        this.event = event;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}