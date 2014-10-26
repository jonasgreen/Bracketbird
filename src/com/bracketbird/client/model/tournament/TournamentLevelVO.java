package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.LevelType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TournamentLevelVO implements Serializable{
    private static final long serialVersionUID = -2149176908460343264L;

    private LevelType tournamentLevelType;

    //the index in the tournamnt
    private Integer index;

    //**************************
    //     Building mathces
    //**************************
    private Integer numberOfGroups;

    //how many teams are allowed to enter this part
    private Integer maxNumberOfTeams;

    //In seeding play
    private Integer numberOfMatches;

    //In group play: how many times play each team each other
    private Integer numberOfRepeats;

    //In cup play: single/double elimination
    private Integer eliminationType;


    //**************************
    //     Point calculation
    //**************************
    private Integer pointsOfVictory;

    private Integer pointsOfDraw;


    //**************************
    //     Finding ranking when teams have same points
    //**************************
    private List<Integer> rankingRules = new ArrayList<Integer>();


    public TournamentLevelVO() {

    }

    public Integer getMaxNumberOfTeams() {
        return maxNumberOfTeams;
    }

    public void setMaxNumberOfTeams(Integer maxNumberOfTeams) {
        this.maxNumberOfTeams = maxNumberOfTeams;
    }

    public Integer getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(Integer numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
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

    public LevelType getTournamentLevelType() {
        return tournamentLevelType;
    }

    public void setTournamentLevelType(LevelType tournamentLevelType) {
        this.tournamentLevelType = tournamentLevelType;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}