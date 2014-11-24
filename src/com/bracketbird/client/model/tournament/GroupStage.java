package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.StageRoundsFactory;
import com.bracketbird.client.model.StageType;
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


    private GroupStage() {
        super();
    }


    public GroupStage(Tournament t) {
        super(t, StageType.group);
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

    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        ranker = null;
        if (getState().equals(LevelState.finished)) {
            if (endingTeams.isEmpty()) {
                //this means all groups are updateEndingTeams (ie has ending teams)
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
                else{
                    return LevelState.donePlaying;
                }
            }
            else{
                //endingTeams has been set by outside
                return newState;
            }
        }
        else{
            endingTeams = new ArrayList<List<Team>>();
            return newState;
        }
    }


    public String getName() {
        if (groups.size() == 0) {
            return "Groups";
        }
        return groups.size() == 1 ? "1 group" : (groups.size() + " groups");
    }

    public List<Group> getGroups() {
        return groups;
    }


    public Group getGroup(GroupId modelId) {
        for (Group group : groups) {
            if(group.getId().equals(modelId)){
                return group;
            }
        }
        return null;
    }
}

