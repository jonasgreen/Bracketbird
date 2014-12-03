package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.StageRoundsFactory;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.GroupId;
import com.bracketbird.client.pages.matches.FinalGroupStageRanker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupStage extends Stage {
    private static final long serialVersionUID = -7946599332097281558L;

    private List<Group> groups = new ArrayList<Group>();
    private FinalGroupStageRanker ranker;


    public GroupStage(Tournament t) {
        super(t);
    }

    @Override
    protected void clear() {
        super.clear();
        groups = new ArrayList<Group>();
    }

    @Override
    public void layoutMatches(boolean fromClient) {
        setupStartingTeams();

        groups = new GroupBuilder(this, startingTeams).getGroups();
        for (Group group : groups) {
            group.layoutMatches();
            group.initState();
        }

        rounds = new StageRoundsFactory(groups).getRounds();
        updateState(fromClient);
    }

    public LevelState calculateState() {
        return stateBasedOnChildren(groups);
    }


    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        ranker = null;
        if (newState.equals(LevelState.finished)) {
            if (endingTeams.isEmpty()) {
                if (groups.size() == 1) {
                    return handleOneFinishedGroup(newState);
                }
                else {
                    return handleMultipleFinishedGroups(newState);
                }
            }
            else {
                //endingTeams has been set by outside
                return newState;
            }
        }
        else {
            endingTeams = new ArrayList<List<Team>>();
            return newState;
        }
    }

    private LevelState handleMultipleFinishedGroups(LevelState newState) {
        ranker = new FinalGroupStageRanker(this);
        if (ranker.allTeamsHasUniquePositions()) {
            for (Position p : ranker.getPositions()) {
                List<Team> oneTeamList = new ArrayList<Team>();
                oneTeamList.add(p.getPointsCounters().get(0).getTeam());
                endingTeams.add(oneTeamList);
            }
            ranker = null;
            return newState;
        }
        else {
            return LevelState.donePlaying;
        }
    }

    private LevelState handleOneFinishedGroup(LevelState newState) {
        //only one team in each list
        for (Team t : groups.get(0).getEndingTeams()) {
            List<Team> list = new ArrayList<Team>();
            list.add(t);
            endingTeams.add(list);
        }
        return newState;
    }


    public String getName() {
        if (groups.size() == 0) {
            return "Groups";
        }
        return groups.size() == 1 ? "1 group" : (groups.size() + " groups");
    }

    @Override
    public String getRoundName(Round round) {
        return round.getRoundNumber() + ". round";
    }

    public List<Group> getGroups() {
        return groups;
    }


    public Group getGroup(GroupId modelId) {
        for (Group group : groups) {
            if (group.getId().equals(modelId)) {
                return group;
            }
        }
        return null;
    }


}

