package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Ladder extends AbstractRanking {

    private Map<Integer, AbstractRanking> children = new HashMap<Integer, AbstractRanking>();


    public void addAll(List<TeamStatistics> teamStats) {
        for (TeamStatistics teamStat : teamStats) {
            add(teamStat);
        }
    }

    public void add(TeamStatistics teamStat){
        Integer value = getValue(teamStat);
        AbstractRanking ranking = children.get(value);
        if(ranking == null){
            ranking = new Step(this, teamStat);
            children.put(value, ranking);
        }
        else if(ranking instanceof Step){
            //Replace step by ladder. If no more ladders exist, add to step.
            Ladder nextLadder = createNextLadder();
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

    protected abstract Ladder createNextLadder();


    protected abstract Integer getValue(TeamStatistics stat);

}
