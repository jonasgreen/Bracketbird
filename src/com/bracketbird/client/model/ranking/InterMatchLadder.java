package com.bracketbird.client.model.ranking;

import com.bracketbird.client.ranking.TeamStatistics;

public class InterMatchLadder extends RankingLadder {


    public InterMatchLadder(LadderFactory factoryOfNext, RankingLadder parent, Integer value) {
        super(factoryOfNext, parent, value);
    }

    protected Integer getValue(TeamStatistics stat){
        return -1;//TODO
    }


}
