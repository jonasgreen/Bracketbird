package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

public class ScoreDifferenceLadder extends RankingLadder {


    public ScoreDifferenceLadder(RankingLadder parent, Integer value) {
        super(parent, value);
    }

    protected Integer getValue(TeamStatistics stat){
        return stat.getGoalDifference();
    }



}
