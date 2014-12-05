package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

public abstract class PointLadder extends Ladder {



    protected Integer getValue(TeamStatistics stat){
        return stat.getPoints();
    }

    protected Ladder createNextLadder(){
        return null;
    }

}
