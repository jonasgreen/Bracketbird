package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.ArrayList;
import java.util.List;

public class RankingStep extends Ranking {

    private List<TeamStatistics> teamStatistics = new ArrayList<TeamStatistics>();

    public RankingStep(Integer id, RankingLadder parent) {
        super(parent, id);
    }

    public List<TeamStatistics> getTeamStatistics() {
        return teamStatistics;
    }

    @Override
    protected void appendStepsToList(List<RankingStep> steps) {
        steps.add(this);
    }

    @Override
    public void add(TeamStatistics teamStat) {
        teamStatistics.add(teamStat);
    }

    @Override
    public void remove(TeamStatistics teamStat) {
        teamStatistics.remove(teamStat);
        if(teamStatistics.isEmpty()){
            parent.removeChild(this);
        }
    }


}
