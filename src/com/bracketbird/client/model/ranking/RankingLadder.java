package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RankingLadder extends Ranking {

    private Map<Integer, Ranking> children = new HashMap<Integer, Ranking>();

    //Points to a possible next ladder.
    private LadderWheel wheel;


    public void addAll(List<TeamStatistics> teamStats) {
        for (TeamStatistics teamStat : teamStats) {
            add(teamStat);
        }
    }

    public void add(TeamStatistics teamStat){
        Integer value = getValue(teamStat);
        Ranking ranking = children.get(value);
        if(ranking == null){
            ranking = new Step(this, teamStat);
            children.put(value, ranking);
        }
        else if(ranking instanceof Step){
            //Replace step by ladder. If no more ladders exist, add to step.
            RankingLadder nextLadder = createNextLadder();
            if(nextLadder != null){
                children.put(value, nextLadder);
                //The new Ladder holds the new TeamStatistics and the TeamStatistics from the Step.
                nextLadder.addAll(((Step)ranking).getTeamStatistics());
                nextLadder.add(teamStat);
            }
            else{
                //add to existing step
                ranking.add(teamStat);
            }
        }
        else{
            ranking.add(teamStat);
        }
    }

    protected RankingLadder createNextLadder(){
        if(wheel != null){
            return wheel.next();
        }
        return null;
    }


    protected abstract Integer getValue(TeamStatistics stat);

    public void setWheel(LadderWheel wheel) {
        this.wheel = wheel;
    }

    public LadderWheel getWheel() {
        return wheel;
    }
}
