package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

public abstract class ScoreDifferenceLadder extends Ladder {



    protected Integer getValue(TeamStatistics stat){
        return stat.getGoalDifference();
    }

    protected Ladder createNextLadder(){
        return null;
    }

}
