package com.bracketbird.client.model.tournament;

import com.bracketbird.client.ranking.TeamStatistics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Position{
    private List<TeamStatistics> teamStatisticses = new ArrayList<TeamStatistics>();

    public Position(TeamStatistics pc) {
        add(pc);
    }


    public void add(TeamStatistics pc){
        teamStatisticses.add(pc);
    }


    public boolean hasMoreTeams(){
        return teamStatisticses.size() != 1;
    }


    public List<TeamStatistics> getPointsCounters() {
        return teamStatisticses;
    }
}
