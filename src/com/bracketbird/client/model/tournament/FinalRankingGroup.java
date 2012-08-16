package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FinalRankingGroup implements Serializable{
    private static final long serialVersionUID = -7946599332097281558L;
    private List<Team> teams = new ArrayList<Team>();
    private List<Match> matches = new ArrayList<Match>();


    public FinalRankingGroup(List<Team> teams, List<Match> matches) {
        this.teams = teams;
        this.matches = matches;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}

