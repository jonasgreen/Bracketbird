package com.bracketbird.client.model.tournament;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StageSettings implements Serializable {
    private static final long serialVersionUID = -2739344755726704432L;


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


    public StageSettings() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StageSettings that = (StageSettings) o;

        if (eliminationType != null ? !eliminationType.equals(that.eliminationType) : that.eliminationType != null) {
            return false;
        }
        if (maxNumberOfTeams != null ? !maxNumberOfTeams.equals(that.maxNumberOfTeams) : that.maxNumberOfTeams != null) {
            return false;
        }
        if (numberOfGroups != null ? !numberOfGroups.equals(that.numberOfGroups) : that.numberOfGroups != null) {
            return false;
        }
        if (numberOfMatches != null ? !numberOfMatches.equals(that.numberOfMatches) : that.numberOfMatches != null) {
            return false;
        }
        if (numberOfRepeats != null ? !numberOfRepeats.equals(that.numberOfRepeats) : that.numberOfRepeats != null) {
            return false;
        }
        if (pointsOfDraw != null ? !pointsOfDraw.equals(that.pointsOfDraw) : that.pointsOfDraw != null) {
            return false;
        }
        if (pointsOfVictory != null ? !pointsOfVictory.equals(that.pointsOfVictory) : that.pointsOfVictory != null) {
            return false;
        }
        if (rankingRules != null ? !rankingRules.equals(that.rankingRules) : that.rankingRules != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = numberOfGroups != null ? numberOfGroups.hashCode() : 0;
        result = 31 * result + (maxNumberOfTeams != null ? maxNumberOfTeams.hashCode() : 0);
        result = 31 * result + (numberOfMatches != null ? numberOfMatches.hashCode() : 0);
        result = 31 * result + (numberOfRepeats != null ? numberOfRepeats.hashCode() : 0);
        result = 31 * result + (eliminationType != null ? eliminationType.hashCode() : 0);
        result = 31 * result + (pointsOfVictory != null ? pointsOfVictory.hashCode() : 0);
        result = 31 * result + (pointsOfDraw != null ? pointsOfDraw.hashCode() : 0);
        result = 31 * result + (rankingRules != null ? rankingRules.hashCode() : 0);
        return result;
    }
}