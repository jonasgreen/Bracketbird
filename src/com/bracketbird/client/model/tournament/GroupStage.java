package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.GroupScheduler;
import com.bracketbird.client.model.LevelType;
import com.bracketbird.client.model.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupStage extends TournamentStage {
    private static final long serialVersionUID = -7946599332097281558L;

    private List<Group> groups = new ArrayList<Group>();

    private GroupStage() {
        super();
    }


    public GroupStage(Tournament t) {
        super(t, LevelType.group);
    }

    @Override
    public Scheduler getScheduler() {
        groups = new GroupBuilder(this, startingTeams).getGroups();
        return new GroupScheduler(this, groups);
    }


    public void clear(boolean fromClient) {
        groups = new ArrayList<Group>();
        super.clear(fromClient);
    }


    public String getName() {
        if(groups.size() == 0){
            return "Groups";
        }
        return groups.size() == 1 ? "1 group": (groups.size() + " groups");
    }

    public List<Group> getGroups() {
        return groups;
    }



}

