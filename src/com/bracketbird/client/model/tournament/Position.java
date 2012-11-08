package com.bracketbird.client.model.tournament;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Position{
    private List<TeamResultSum> teamResultSums = new ArrayList<TeamResultSum>();

    public Position(TeamResultSum pc) {
        add(pc);
    }


    public void add(TeamResultSum pc){
        teamResultSums.add(pc);
    }


    public boolean hasMoreTeams(){
        return teamResultSums.size() != 1;
    }


    public List<TeamResultSum> getPointsCounters() {
        return teamResultSums;
    }
}