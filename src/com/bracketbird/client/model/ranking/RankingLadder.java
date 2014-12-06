package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.*;

public abstract class RankingLadder extends Ranking {

    private Map<Integer, Ranking> children = new HashMap<Integer, Ranking>();

    //Points to a possible next ladder.
    private LadderWheel wheel;

    public RankingLadder(RankingLadder parent, Integer id) {
        super(parent, id);
    }


    public List<RankingStep> getTotalRanking(){
        List<RankingStep> list = new ArrayList<RankingStep>();
        appendStepsToList(list);
        return list;
    }

    @Override
    protected void appendStepsToList(List<RankingStep> list){
        List<Integer> keys = new ArrayList<Integer>(children.keySet());
        Collections.sort(keys);
        for (Integer key : keys) {
            children.get(key).appendStepsToList(list);
        }
    }


    @Override
    public void remove(TeamStatistics teamStat) {
        Ranking ranking = children.get(getValue(teamStat));
        if(ranking != null){
            ranking.remove(teamStat);
        }
    }

    @Override
    public void add(TeamStatistics teamStat){
        Integer value = getValue(teamStat);
        Ranking ranking = children.get(value);
        if(ranking == null){
            ranking = createNext(value);
            children.put(value, ranking);
        }
        ranking.add(teamStat);
    }

    //Called from child, when child is empty.
    public void removeChild(Ranking child){
        children.remove(child.getId());
        if(children.isEmpty() && parent != null){
            parent.removeChild(this);
        }
    }




    protected Ranking createNext(Integer id){
        Ranking next = null;
        if(wheel != null){
            next = wheel.next(this, id);
        }
        //At the end of the RankingLadder line there is one or more RankingSteps
        return next != null ? next : new RankingStep(id, this);
    }


    protected abstract Integer getValue(TeamStatistics stat);

    public void setWheel(LadderWheel wheel) {
        this.wheel = wheel;
    }

    public LadderWheel getWheel() {
        return wheel;
    }
}
