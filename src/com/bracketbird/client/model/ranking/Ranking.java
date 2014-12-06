package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.List;

public abstract class Ranking {

    //Only unique in the context the ranking exist. Often as a child of another ranking.
    protected final Integer id;
    protected final RankingLadder parent;

    public Ranking(RankingLadder parent, Integer value) {
        this.id = value;
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public Ranking getParent() {
        return parent;
    }

    protected abstract void appendStepsToList(List<RankingStep> steps);

    public abstract void add(TeamStatistics teamStat);

    public abstract void remove(TeamStatistics teamStat);

}