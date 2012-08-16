package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

/**
 *
 */
public class TeamResultSum {

    private Team team;
    private int scoredGoals = 0;
    private int receivedGoals = 0;
    private int wonMatches = 0;
    private int lostMatchs = 0;
    private int drawMatchs = 0;
    private int playedMatches = 0;
    private int points = 0;


    public TeamResultSum(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public void setReceivedGoals(int receivedGoals) {
        this.receivedGoals = receivedGoals;
    }

    public int getWonMatches() {
        return wonMatches;
    }

    public void setWonMatches(int wonMatches) {
        this.wonMatches = wonMatches;
    }

    public int getLostMatchs() {
        return lostMatchs;
    }

    public void setLostMatchs(int lostMatchs) {
        this.lostMatchs = lostMatchs;
    }

    public int getDrawMatchs() {
        return drawMatchs;
    }

    public void setDrawMatchs(int drawMatchs) {
        this.drawMatchs = drawMatchs;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

    public int getPoints() {
        return points;
    }

    public int getScoredGoalsDiff(){
        return scoredGoals - receivedGoals;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "TeamResultSum{" +
                "playingTeam=" + team +
                ", scoredGoals=" + scoredGoals +
                ", receivedGoals=" + receivedGoals +
                ", wonMatches=" + wonMatches +
                ", lostMatchs=" + lostMatchs +
                ", drawMatchs=" + drawMatchs +
                ", playedMatches=" + playedMatches +
                ", points=" + points +
                '}';
    }
}
