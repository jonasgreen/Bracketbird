package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.StageRoundsFactory;
import com.bracketbird.client.model.StageType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupStage extends Stage {
    private static final long serialVersionUID = -7946599332097281558L;

    private List<Group> groups = new ArrayList<Group>();

    private GroupStage() {
        super();
    }


    public GroupStage(Tournament t) {
        super(t, StageType.group);
    }


    public void clear(boolean fromClient) {
        groups = new ArrayList<Group>();
        super.clear(fromClient);
    }

    @Override
    public void layoutMatches(boolean fromClient) {
        setupStartingTeams();

        groups = new GroupBuilder(this, startingTeams).getGroups();
        for (Group group : groups) {
            group.layoutMatches();
        }

        rounds = new StageRoundsFactory(groups).getRounds();
        updateState(fromClient);
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


}

