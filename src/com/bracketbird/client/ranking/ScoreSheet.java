package com.bracketbird.client.ranking;

/**
 *  Immutable
 */
public class ScoreSheet {

    private int points = 0;

    private int scoredGoals = 0;
    private int receivedGoals = 0;

    private int playedMatches = 0;
    private int wonMatches = 0;
    private int lostMatches = 0;
    private int drawMatches = 0;

    protected ScoreSheet(){

    }

    public ScoreSheet add(ScoreSheet s){
        ScoreSheet toUse = s == null ? new EmptyScoreSheet() : s;

        ScoreSheet sheet = new ScoreSheet();
        sheet.points += toUse.points;
        sheet.scoredGoals += toUse.scoredGoals;
        sheet.receivedGoals += toUse.receivedGoals;
        sheet.playedMatches += toUse.playedMatches;
        sheet.wonMatches += toUse.wonMatches;
        sheet.lostMatches += toUse.wonMatches;
        sheet.drawMatches += toUse.drawMatches;

        return sheet;
    }

    public ScoreSheet subtract(ScoreSheet s){
        ScoreSheet toUse = s == null ? new EmptyScoreSheet() : s;

        ScoreSheet sheet = new ScoreSheet();
        sheet.points -= toUse.points;
        sheet.scoredGoals -= toUse.scoredGoals;
        sheet.receivedGoals -= toUse.receivedGoals;
        sheet.playedMatches -= toUse.playedMatches;
        sheet.wonMatches -= toUse.wonMatches;
        sheet.lostMatches -= toUse.wonMatches;
        sheet.drawMatches -= toUse.drawMatches;

        return sheet;
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

    public int getGoalDifference(){
        return scoredGoals - receivedGoals;
    }

    void setPoints(int points) {
        this.points = points;
    }

    void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    void setReceivedGoals(int receivedGoals) {
        this.receivedGoals = receivedGoals;
    }

    void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

    void setWonMatches(int wonMatches) {
        this.wonMatches = wonMatches;
    }

    void setLostMatches(int lostMatches) {
        this.lostMatches = lostMatches;
    }

    void setDrawMatches(int drawMatches) {
        this.drawMatches = drawMatches;
    }



}
