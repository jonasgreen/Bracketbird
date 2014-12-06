package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

public class PointLadder extends RankingLadder {


    public PointLadder(RankingLadder parent, Integer value) {
        super(parent, value);
    }

    protected Integer getValue(TeamStatistics stat){
        return stat.getPoints();
    }


}
