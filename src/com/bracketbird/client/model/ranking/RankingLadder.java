package com.bracketbird.client.model.ranking;

import com.bracketbird.client.ranking.TeamStatistics;

import java.util.*;

public abstract class RankingLadder extends Ranking {

    public static Comparator<Integer> sortingComp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -o1.compareTo(o2);
        }
    };

    private LadderFactory factoryOfNextLadder;
    private Map<Integer, Ranking> children = new HashMap<Integer, Ranking>();

    public RankingLadder(LadderFactory factoryOfNextLadder, RankingLadder parent, Integer id) {
        super(parent, id);
        this.factoryOfNextLadder = factoryOfNextLadder;
    }


    public List<RankingStep> getTotalRanking(){
        List<RankingStep> list = new ArrayList<RankingStep>();
        appendStepsToList(list);
        return list;
    }

    @Override
    protected void appendStepsToList(List<RankingStep> list){
        List<Integer> keys = new ArrayList<Integer>(children.keySet());

        Collections.sort(keys, sortingComp);
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
        if(factoryOfNextLadder != null){
            return factoryOfNextLadder.create(this, id);
        }

        //At the end of the RankingLadder line there is one or more RankingSteps
        return new RankingStep(id, this);
    }


    protected abstract Integer getValue(TeamStatistics stat);

    public Map<Integer, Ranking> getChildren() {
        return children;
    }
}
