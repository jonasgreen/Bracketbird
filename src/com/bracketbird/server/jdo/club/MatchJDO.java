package com.bracketbird.server.jdo.club;

import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.JDO;

import javax.jdo.annotations.*;
import java.util.Date;
import java.util.List;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class MatchJDO extends JDO {


	
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    //foreign key
    @Persistent
    private Key clubKey;

    @Persistent
    private Key TournamentKey;

    private Key teamKeyOut;

    private Key teamKeyHome;

    //used in group-play
    @Persistent
    private Integer group;

    //what round does this match belong to
    @Persistent
    private Long round;


    //the order of the match in a subtournament
    private Integer order;


    //indirectly gives the set score.
    @Persistent
    private List<Integer> scoresA;

    @Persistent
    private List<Integer> scoresB;





    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;


    public MatchJDO() {

    }


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getClubKey() {
        return clubKey;
    }

    public void setClubKey(Key clubKey) {
        this.clubKey = clubKey;
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


    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public Key getTeamKeyOut() {
        return teamKeyOut;
    }

    public void setTeamKeyOut(Key teamKeyOut) {
        this.teamKeyOut = teamKeyOut;
    }

    public Key getTeamKeyHome() {
        return teamKeyHome;
    }

    public void setTeamKeyHome(Key teamKeyHome) {
        this.teamKeyHome = teamKeyHome;
    }

    public List<Integer> getScoresA() {
        return scoresA;
    }

    public void setScoresA(List<Integer> scoresA) {
        this.scoresA = scoresA;
    }

    public List<Integer> getScoresB() {
        return scoresB;
    }

    public void setScoresB(List<Integer> scoresB) {
        this.scoresB = scoresB;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Key getTournamentKey() {
        return TournamentKey;
    }

    public void setTournamentKey(Key tournamentKey) {
        TournamentKey = tournamentKey;
    }
}