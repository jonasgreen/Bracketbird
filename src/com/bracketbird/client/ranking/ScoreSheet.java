package com.bracketbird.client.ranking;

import com.bracketbird.client.util.MathOperation;
import com.bracketbird.client.util.Minus;
import com.bracketbird.client.util.Plus;

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
        if(s == null){
            return this;
        }

        return mathOperation(Plus.get(), this, s);
    }

    public ScoreSheet subtract(ScoreSheet s){
        if(s == null){
            return this;
        }

        return mathOperation(Minus.get(), this, s);
    }



    private ScoreSheet mathOperation(MathOperation m, ScoreSheet s1, ScoreSheet s2){
        ScoreSheet r = new ScoreSheet();
        r.points = m.operate(s1.points, s2.points);
        r.scoredGoals = m.operate(s1.scoredGoals, s2.scoredGoals);
        r.receivedGoals = m.operate(receivedGoals, s2.receivedGoals);
        r.playedMatches = m.operate(playedMatches, s2.playedMatches);
        r.wonMatches = m.operate(wonMatches, s2.wonMatches);
        r.lostMatches = m.operate(lostMatches, s2.wonMatches);
        r.drawMatches = m.operate(drawMatches, s2.drawMatches);

        return r;
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
