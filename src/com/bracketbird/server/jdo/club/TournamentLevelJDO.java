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
public class TournamentLevelJDO extends JDO {


    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private com.google.appengine.api.datastore.Key key;

    @Persistent
    private Key tournamentKey;

    @Persistent
    private Integer type;

    //what index in the overall tournament does this level have.
    private Integer index;

    //list of this subtournaments matches
    @Persistent(defaultFetchGroup = "true")
    private List<Key> matches;

    //playingTeamJDO keys
    @Persistent(defaultFetchGroup = "true")
    private List<Key> teams = new ArrayList<Key>();


    //****************SETTINGS*****************


    //**************************
    //     Building mathces
    //**************************
    @Persistent
    private Integer numberOfGroups;

    //how many teams are allowed to enter this part
    @Persistent
    private Integer maxNumberOfTeams;

    //In seeding play
    @Persistent
    private Integer numberOfMatches;

    //In group play: how many times play each team each other
    @Persistent
    private Integer numberOfRepeats;

    //In cup play: single/double elimination
    @Persistent
    private Integer eliminationType;


    //**************************
    //     Point calculation
    //**************************
    @Persistent
    private Integer pointsOfVictory;

    @Persistent
    private Integer pointsOfDraw;


    //**************************
    //     Finding ranking when teams have same points
    //**************************
    private List<Integer> rankingRules = new ArrayList<Integer>();


    

    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public TournamentLevelJDO() {

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

    public List<Key> getMatches() {
        return matches;
    }

    public void setMatches(List<Key> matches) {
        this.matches = matches;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Key> getTeams() {
        return teams;
    }

    public void setTeams(List<Key> teams) {
        this.teams = teams;
    }


    public Integer getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(Integer numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public Integer getMaxNumberOfTeams() {
        return maxNumberOfTeams;
    }

    public void setMaxNumberOfTeams(Integer maxNumberOfTeams) {
        this.maxNumberOfTeams = maxNumberOfTeams;
    }

    public Integer getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(Integer numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public Integer getNumberOfRepeats() {
        return numberOfRepeats;
    }

    public void setNumberOfRepeats(Integer numberOfRepeats) {
        this.numberOfRepeats = numberOfRepeats;
    }

    public Integer getEliminationType() {
        return eliminationType;
    }

    public void setEliminationType(Integer eliminationType) {
        this.eliminationType = eliminationType;
    }

    public Integer getPointsOfVictory() {
        return pointsOfVictory;
    }

    public void setPointsOfVictory(Integer pointsOfVictory) {
        this.pointsOfVictory = pointsOfVictory;
    }

    public Integer getPointsOfDraw() {
        return pointsOfDraw;
    }

    public void setPointsOfDraw(Integer pointsOfDraw) {
        this.pointsOfDraw = pointsOfDraw;
    }

    public List<Integer> getRankingRules() {
        return rankingRules;
    }

    public void setRankingRules(List<Integer> rankingRules) {
        this.rankingRules = rankingRules;
    }

    public Key getTournamentKey() {
        return tournamentKey;
    }

    public void setTournamentKey(Key tournamentKey) {
        this.tournamentKey = tournamentKey;
    }

    @Override
    public String toString() {
        return "SubtournamentJDO{" +
                ", matches=" + matches +
                ", teams=" + teams +
                "}";
    }

    public void toString(StringBuffer sb, int tab){
        WriterIt.appendTab(sb, tab);
        sb.append("SubtournamentJDO\n");
        WriterIt.append(sb, "matches", matches, tab+1);
        WriterIt.append(sb, "teams", teams, tab+1);



    }
}