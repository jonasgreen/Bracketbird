package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

public class InterMatchLadder extends RankingLadder {


    public InterMatchLadder(RankingLadder parent, Integer value) {
        super(parent, value);
    }

    protected Integer getValue(TeamStatistics stat){
        return stat.getGoalDifference();//TODO
    }


}
