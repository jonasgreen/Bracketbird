package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.keys.TeamId;

import java.io.Serializable;
import java.util.List;


/**
 *
 */
public class LevelFinishedVO implements Serializable{
    private static final long serialVersionUID = 2179820059866310149L;

    private List<TeamId> rankOfTeams;

    public LevelFinishedVO() {
    }

    public LevelFinishedVO(List<TeamId> rankOfTeams) {
        this.rankOfTeams = rankOfTeams;
    }

    public List<TeamId> getRankOfTeams() {
        return rankOfTeams;
    }

    public void setRankOfTeams(List<TeamId> rankOfTeams) {
        this.rankOfTeams = rankOfTeams;
    }
}