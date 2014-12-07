package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

import java.util.List;

/**
 *
 */
public class TeamStatistics {

    private StageSettings settings;
    private final Team team;

    private int points = 0;

    private int scoredGoals = 0;
    private int receivedGoals = 0;

    private int playedMatches = 0;
    private int wonMatches = 0;
    private int lostMatches = 0;
    private int drawMatches = 0;


    public TeamStatistics(StageSettings settings, Team team) {
        this.settings = settings;
        this.team = team;
    }


    public void update(Match match, Result oldResult, Result newResult){
        if(oldResult != null){
            removeResult(match, oldResult);
        }
        if(newResult != null){
            addResult(match, newResult);
        }
    }


    private void addResult(Match match, Result result) {
        update(true, match, result);
    }

    private void removeResult(Match match, Result result) {
        update(false, match, result);
    }

    private void update(boolean isAdding, Match match, Result result){
        updateMatchesAndPoints(isAdding, match, result);
        updateGoals(isAdding, match, result);
    }

    private void updateMatchesAndPoints(boolean isAdding, Match match, Result result) {
        playedMatches += getValue(isAdding, 1);

        if (result.isDraw()) {
            drawMatches += getValue(isAdding, 1);
            points += settings.getPointsOfDraw();
        }
        else if (isWinningTeam(match, result)) {
            wonMatches += getValue(isAdding, 1);
            points += getValue(isAdding, settings.getPointsOfVictory());
        }
        else {
            lostMatches += getValue(isAdding, 1);
        }
    }

    private boolean isWinningTeam(Match m, Result result){
        if(m.isHomeTeam(team)){
            return result.homeIsWinning();
        }
        return result.outIsWinning();
    }

    private int getValue(boolean isAdding, int value) {
        return isAdding ? value : -value;
    }

    private void updateGoals(boolean isAdding, Match match, Result result) {
        boolean isHomeTeam = match.isHomeTeam(team);
        addScoredGoals(isAdding, isHomeTeam ? result.getScoresHome() : result.getScoresOut());
        addReceivedGoals(isAdding, isHomeTeam ? result.getScoresOut() : result.getScoresHome());
    }

    private void addScoredGoals(boolean isAdding, List<Integer> scores) {
        for (Integer score : scores) {
            scoredGoals += getValue(isAdding, score);
        }
    }

    private void addReceivedGoals(boolean isAdding, List<Integer> scores) {
        for (Integer score : scores) {
            receivedGoals += getValue(isAdding, score);
        }
    }


    public Team getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public int getWonMatches() {
        return wonMatches;
    }

    public int getLostMatches() {
        return lostMatches;
    }

    public int getDrawMatches() {
        return drawMatches;
    }

    public int getGoalDifference() {
        return scoredGoals - receivedGoals;
    }

    @Override
    public String toString() {
        return team.getName()+"\t[" +
                "points=" + points +
                ", GoalDiff=" + (scoredGoals - receivedGoals) +
                ", scoredGoals=" + scoredGoals +
                ", receivedGoals=" + receivedGoals +
                ", wonMatches=" + wonMatches +
                ", lostMatches=" + lostMatches +
                ", drawMatches=" + drawMatches +
                ", playedMatches=" + playedMatches +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatistics that = (TeamStatistics) o;

        if (!team.equals(that.team)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return team.hashCode();
    }
}
