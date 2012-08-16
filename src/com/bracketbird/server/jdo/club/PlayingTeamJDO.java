package com.bracketbird.server.jdo.club;

import com.bracketbird.server.dao.*;
import com.google.appengine.api.datastore.Key;
import com.bracketbird.clientcore.util.*;

import javax.jdo.annotations.*;
import java.util.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class PlayingTeamJDO extends JDO {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;


    @Persistent
    private Key clubKey;


    @Persistent
    private Key tournamentKey;
    

    @Persistent
    private String name;


    @Persistent
    private String info;


    @Persistent
    private Integer seeding;

    //(single, double, team).
    @Persistent
    private Integer type;

    @Persistent(defaultFetchGroup = "true")
    private List<Key> members = new ArrayList<Key>();

    @Persistent(defaultFetchGroup = "true")
    private List<String> nonMembers = new ArrayList<String>();


    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public PlayingTeamJDO() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Key> getMembers() {
        return members;
    }

    public void setMembers(List<Key> members) {
        this.members = members;
    }

    public Integer getSeeding() {
        return seeding;
    }

    public void setSeeding(Integer seeding) {
        this.seeding = seeding;
    }

    public List<String> getNonMembers() {
        return nonMembers;
    }

    public void setNonMembers(List<String> nonMembers) {
        this.nonMembers = nonMembers;
    }

    public Key getTournamentKey() {
        return tournamentKey;
    }

    public void setTournamentKey(Key tournamentKey) {
        this.tournamentKey = tournamentKey;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "PlayingTeamJDO{" +
                ", name='" + name  +
                ", members=" + StringUtil.toString(members) +
                ", nonMembers=" + StringUtil.toString(nonMembers) +
                "}";
    }

    public void toString(StringBuffer sb, int tab){
        WriterIt.appendTab(sb, tab);
        sb.append("PlayingTeamJDO\n");
        WriterIt.append(sb, "key", key, tab + 1);
        WriterIt.append(sb, "name", name, tab+1);
        WriterIt.append(sb, "members", members, tab+1);
        WriterIt.append(sb, "nonmembers", nonMembers, tab+1);



    }

    public Key getClubKey() {
        return clubKey;
    }

    public void setClubKey(Key clubKey) {
        this.clubKey = clubKey;
    }
}