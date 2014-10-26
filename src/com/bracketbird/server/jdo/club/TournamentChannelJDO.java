package com.bracketbird.server.jdo.club;

import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.*;

import javax.jdo.annotations.*;
import java.util.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class TournamentChannelJDO extends JDO {



    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    private Key tournamentKey;

    @Persistent(defaultFetchGroup = "true")
    private List<String> clients = new ArrayList<String>();


    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public TournamentChannelJDO() {

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

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    public Key getTournamentKey() {
        return tournamentKey;
    }

    public void setTournamentKey(Key tournamentKey) {
        this.tournamentKey = tournamentKey;
    }
}