package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AGroup implements Serializable{
    private static final long serialVersionUID = -7946599332097281558L;

    private String name;
    private List<Team> teams = new ArrayList<Team>();
    private List<Round> rounds = new ArrayList<Round>();

    public AGroup(String name) {
        super();
        this.name = name;
    }

    public void add(Team pt){
        teams.add(pt);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "AGroup{" +
                "teamsSize=" + teams.size() +
                '}';
    }

    public List<Match> getMatches() {
        List<Match> list = new ArrayList<Match>();
        for (Round round : rounds) {
            list.addAll(round.getMatches());
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

