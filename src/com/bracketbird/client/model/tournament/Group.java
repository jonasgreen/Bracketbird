package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.GroupScheduler;
import com.bracketbird.client.model.Scheduler;
import com.bracketbird.client.model.keys.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Group extends TournamentLevel {
    private static final long serialVersionUID = -7946599332097281558L;

    private List<AGroup> groups = new ArrayList<AGroup>();

    public Group() {
        super();
    }


    public Group(Tournament t) {
        super(t);
    }

    @Override
    public Scheduler getScheduler() {
        groups = new GroupBuilder(getStageSettings().getNumberOfGroups(), startingTeams).getGroups();
        return new GroupScheduler(this, groups);
    }


    public void clear(boolean notifyParent, boolean fromClient) {
        groups = new ArrayList<AGroup>();
        super.clear(notifyParent, fromClient);
    }




    public String getName() {
        if(groups.size() == 0){
            return "Groups";
        }
        return groups.size() == 1 ? "1 group": (groups.size() + " groups");
    }

    public List<AGroup> getGroups() {
        return groups;
    }

    public void resultChanged(Match m) {

    }
}

