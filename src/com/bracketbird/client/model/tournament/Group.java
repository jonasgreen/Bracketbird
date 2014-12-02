package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.model.GroupRoundsFactory;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.GroupId;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.pages.matches.GroupPositions;
import com.bracketbird.clientcore.model.StateModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Group extends StateModel<GroupId> {
    private static final long serialVersionUID = -7946599332097281558L;

    private String name;
    private List<Team> teams = new ArrayList<Team>();
    private List<GroupRound> rounds = new ArrayList<GroupRound>();
    private List<Team> endingTeams = new ArrayList<Team>();
    private GroupPositions groupPositions;

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
        for (GroupRound round : rounds) {
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

    @Override
    public void updateState(boolean fromClient) {
        LevelState newState = calculateState();
        setNewState(newState, fromClient);
    }

    @Override
    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        groupPositions = null;
        if (newState.equals(LevelState.finished)) {
            if (endingTeams.isEmpty()) {
                GroupPositions gp = new GroupPositions(this, getStage().getSettings());
                if (gp.hasTeamsWithSamePosition()) {
                    return LevelState.donePlaying;
                }
                else{
                    for (Position p : gp.getPositionOfTeams()) {
                        endingTeams.add(p.getPointsCounters().get(0).getTeam());
                    }
                    groupPositions = null;
                    return newState;
                }
            }
            else{
                //endingTeams has been set by outside
                return newState;
            }
        }
        else{
            endingTeams = new ArrayList<Team>();
            return newState;
        }
    }

    public void layoutMatches() {
        this.rounds = new GroupRoundsFactory(this).getRounds();
    }

    public void initState() {
        this.state = calculateState();
    }



    @Override
    public LevelState calculateState() {
        LevelState newState = new LevelStateCalculator().stateBasedOnChildren(getMatches());
        groupPositions = null;

        //A Groups children can never be in DonePlaying state.
        if (newState.equals(LevelState.finished)) {
            if (endingTeams.isEmpty()) {
                GroupPositions gp = new GroupPositions(this, getStage().getSettings());
                if (gp.hasTeamsWithSamePosition()) {
                    return LevelState.donePlaying;
                }
                else{
                    for (Position p : gp.getPositionOfTeams()) {
                        endingTeams.add(p.getPointsCounters().get(0).getTeam());
                    }
                    groupPositions = null;
                    return newState;
                }
            }
            else{
                //endingTeams has been set by outside
                return newState;
            }
        }
        else{
            endingTeams = new ArrayList<Team>();
            return newState;
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

    @Override
    public void onChange(StateChangedEvent event) {

    }
}

