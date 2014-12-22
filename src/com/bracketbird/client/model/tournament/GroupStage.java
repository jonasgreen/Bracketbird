package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.GroupStageRoundsFactory;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.GroupId;
import com.bracketbird.client.pages.matches.FinalGroupStageRanker;
import com.bracketbird.client.model.event.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupStage extends Stage {

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

        rounds = new GroupStageRoundsFactory(groups).getRounds();
        updateState(fromClient);
    }

    public LevelState calculateState() {
        ranker = null;

        if (!endingTeams.isEmpty()) {
            return LevelState.finished;
        }

        LevelState childrenState = new LevelStateCalculator().stateBasedOnChildren(getGroups());

        //A Group Stage can only become in state 'DonePlaying' when all children is Finished and equality between one or more
        //teams from these children exists.
        if (childrenState.isDonePlaying()) {
            return LevelState.inProgress;
        }

        if (childrenState.isFinished()) {
            if (groups.size() == 1) {
                setEndingTeams(groups.get(0).getEndingTeams());
                return LevelState.finished;
            }
            else {
                return handleMultipleFinishedGroups();
            }
        }

        return childrenState;
    }


    private LevelState handleMultipleFinishedGroups() {
        ranker = new FinalGroupStageRanker(this);
        if (ranker.allTeamsHasUniquePositions()) {
            for (Position p : ranker.getPositions()) {
                List<Team> oneTeamList = new ArrayList<Team>();
                oneTeamList.add(p.getPointsCounters().get(0).getTeam());
                endingTeams.add(oneTeamList);
            }
            return LevelState.finished;
        }
        else {
            return LevelState.donePlaying;
        }
    }

    private void setEndingTeams(List<Team> teams) {
        //only one team in each list
        for (Team t : teams) {
            List<Team> list = new ArrayList<Team>();
            list.add(t);
            endingTeams.add(list);
        }
    }


    public String getName() {
        if (groups.size() == 0) {
            return "Groups";
        }
        return groups.size() == 1 ? "1 group" : (groups.size() + " groups");
    }

    @Override
    public void onUpdate(UpdateEvent<LevelState> event) {
        endingTeams = new ArrayList<>();
        updateState(event.isFromClient());
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

