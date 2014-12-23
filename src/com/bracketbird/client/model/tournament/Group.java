package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.GroupRoundsFactory;
import com.bracketbird.client.model.LevelStateModel;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.GroupId;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.pages.matches.GroupPositions;
import com.bracketbird.client.model.event.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Group extends LevelStateModel<GroupId> {

    private String name;
    private List<Team> teams = new ArrayList<Team>();
    private List<Round> rounds = new ArrayList<Round>();

    private GroupPositions groupPositions;
    private List<Team> endingTeams = new ArrayList<Team>();
    private GroupRanking ranking;


    private GroupStage stage;

    public Group(GroupStage stage, String name) {
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


    public void updateEndingTeams(List<TeamId> finalRank, boolean fromClient) {
        endingTeams = new ArrayList<Team>();
        for (TeamId id : finalRank) {
            endingTeams.add(getTeam(id));
        }
        updateState(fromClient);
    }

    public Team getTeam(TeamId id) {
        for (Team team : teams) {
            if (id.equals(team.getId())) {
                return team;
            }
        }
        return null;
    }

    void layoutMatches() {
        this.rounds = new GroupRoundsFactory(this).getRounds();
        this.ranking = new GroupRanking(this, getMatches());

    }

    public void initState() {
        this.state = calculateState();
    }


    public LevelState calculateState() {
        groupPositions = null;

        if (!endingTeams.isEmpty()) {
            return LevelState.finished;
        }

        LevelState childrenState = new LevelStateCalculator().stateBasedOnChildren(getMatches());

        //A Groups children can never be in DonePlaying state.
        if (childrenState.equals(LevelState.finished)) {
            groupPositions = new GroupPositions(this, getStage().getSettings());
            if (groupPositions.hasTeamsWithSamePosition()) {
                return LevelState.donePlaying;
            }
            else {
                for (Position p : groupPositions.getPositionOfTeams()) {
                    endingTeams.add(p.getPointsCounters().get(0).getTeam());
                }
                return childrenState;
            }
        }
        else {
            return childrenState;
        }
    }

    public GroupStage getStage() {
        return stage;
    }

    public List<Team> getEndingTeams() {
        return endingTeams;
    }

    public GroupPositions getGroupPositions() {
        return groupPositions;
    }

    //Called from a child - ie. round.
    @Override
    public void onUpdate(UpdateEvent<LevelState> event) {
        endingTeams = new ArrayList<Team>();
        updateState(event.isFromClient());
    }

    public GroupRanking getRanking() {
        return ranking;
    }
}

