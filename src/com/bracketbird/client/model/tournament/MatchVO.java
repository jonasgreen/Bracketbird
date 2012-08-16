package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.keys.TeamId;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class MatchVO implements Serializable {
    private static final long serialVersionUID = 1062096126907375534L;

    //used in group-play
    private String groupName;

    //what round does this match belong to
    private Long round;

    //the order of the match in a subtournament
    private Integer order;

    //used in knockout - to reference between mathces (who meets who).
    private String name;

    private TeamId teamHome;

    private TeamId teamOut;


    private Date createdDate;

    private Date lastChangeDate;

    private Result result;

    private String field;


    public MatchVO() {

    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public void setResult(Result r) {
        result = r;
    }

    public String getField() {
        return field;
    }

    public void setField(String f) {
        this.field = f;
    }

    public Result getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public TeamId getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(TeamId teamHome) {
        this.teamHome = teamHome;
    }

    public TeamId getTeamOut() {
        return teamOut;
    }

    public void setTeamOut(TeamId teamOut) {
        this.teamOut = teamOut;
    }

}