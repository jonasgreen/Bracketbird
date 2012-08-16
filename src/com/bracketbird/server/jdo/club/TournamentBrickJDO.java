package com.bracketbird.server.jdo.club;

import com.bracketbird.server.dao.JDO;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.*;
import java.util.Date;
import java.util.List;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class TournamentBrickJDO extends JDO {


    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    //foreign key
    @Persistent
    private Key userKey;

    @Persistent
    private Key TournamentKey;

    //used in group-play
    @Persistent
    private String tournamentName;

    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public TournamentBrickJDO() {

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

    public Key getTournamentKey() {
        return TournamentKey;
    }

    public void setTournamentKey(Key tournamentKey) {
        TournamentKey = tournamentKey;
    }

    public Key getUserKey() {
        return userKey;
    }

    public void setUserKey(Key userKey) {
        this.userKey = userKey;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}