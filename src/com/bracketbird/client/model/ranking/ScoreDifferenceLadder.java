package com.bracketbird.client.model.ranking;

import com.bracketbird.client.ranking.TeamStatistics;

public class ScoreDifferenceLadder extends RankingLadder {


    public ScoreDifferenceLadder(LadderFactory factoryOfNext, RankingLadder parent, Integer value) {
        super(factoryOfNext, parent, value);
    }

    protected Integer getValue(TeamStatistics stat){
        return stat.getTotalScoreSheet().getGoalDifference();
    }



}
