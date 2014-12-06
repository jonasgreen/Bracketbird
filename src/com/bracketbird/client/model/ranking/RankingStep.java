package com.bracketbird.client.model.ranking;

import com.bracketbird.client.model.tournament.TeamStatistics;

import java.util.List;

public class RankingStep extends Ranking {

    private RankingLadder parent;
    private List<TeamStatistics> teamStatistics;

    public RankingStep(RankingLadder parent, TeamStatistics teamStatistics) {
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
