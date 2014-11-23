package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.GroupRoundsFactory;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.GroupId;
import com.bracketbird.client.pages.matches.GroupPositions;
import com.bracketbird.clientcore.model.PlayableModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Group extends PlayableModel<GroupId> {
    private static final long serialVersionUID = -7946599332097281558L;

    private String name;
    private List<Team> teams = new ArrayList<Team>();
    private List<GroupRound> rounds = new ArrayList<GroupRound>();
    private List<Team> endingTeams = new ArrayList<Team>();
    private GroupStage stage;

    public Group(GroupStage stage, String name) {
        super();
        this.stage = stage;
        this.name = name;
        this.state = LevelState.ready;
    }

    public void add(Team pt) {
        teams.add(pt);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<GroupRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<GroupRound> rounds) {
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

    @Override
    public GroupStage getParent() {
        return stage;
    }

    @Override
    public LevelState calculateState() {
        LevelState state = calculateState(rounds);
        if (state.isFinished()) {
            GroupPositions gp = new GroupPositions(this, getParent().getSettings());
            if (gp.hasTeamsWithSamePosition()) {
                return LevelState.donePlaying;
            }
            List<Team> teams = new ArrayList<Team>();
            for (Position p : gp.getPositionOfTeams()) {
                teams.add(p.getPointsCounters().get(0).getTeam());
            }
            endingTeams = teams;
        }
        else{
            endingTeams = new ArrayList<Team>();
        }
        return state;
    }

    @Override
    protected void stateChanged() {

    }

    public void layoutMatches() {
        this.rounds = new GroupRoundsFactory(this).getRounds();
        initState();
    }

    private void initState() {
        this.state = calculateState();
    }
}

