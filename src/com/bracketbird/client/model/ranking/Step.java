package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.List;

public class Step extends AbstractRanking {

    private Ladder parent;
    private List<TeamStatistics> teamStatistics;

    public Step(Ladder parent, TeamStatistics teamStatistics) {
        this.parent = parent;
        this.teamStatistics.add(teamStatistics);
    }

    public List<TeamStatistics> getTeamStatistics() {
        return teamStatistics;
    }

    @Override
    public void add(TeamStatistics teamStat) {
        teamStatistics.add(teamStat);
    }
}
