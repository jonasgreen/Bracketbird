package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupBuilder {

    private GroupName groupNames = new GroupName();
    private List<Group> groups = new ArrayList<Group>();
    private List<Team> teams;

    private int index = 0;
    private boolean goingRight = false;
    private boolean hasBeenAtTurningPoint = true;
    private GroupStage stage;

    public GroupBuilder(GroupStage stage, List<Team> teams) {
        super();
        this.stage = stage;
        this.teams = teams;
        int i = 0;
        while (i < stage.getStageSettings().getNumberOfGroups()) {
            groups.add(new Group(groupNames.next()));
            i++;
        }
        buildGroups();
    }


    private void buildGroups() {
        //added to the same group
        if(groups.size() == 1){
            Group aGroup = groups.get(0);
            for (Team team : teams) {
                aGroup.add(team);
            }
            return;
        }

        for (Team team : teams) {
            groups.get(getIndex()).add(team);
        }
    }

    private int getIndex() {
        if (goingRight) {
            if (index == groups.size() - 1) {
                if (hasBeenAtTurningPoint) {
                    goingRight = !goingRight;
                    hasBeenAtTurningPoint = false;
                    return index--;
                }
                else {
                    hasBeenAtTurningPoint = true;
                    return index;
                }
            }
            else {
                return index++;
            }
        }
        else {
            if (index == 0) {
                if (hasBeenAtTurningPoint) {
                    goingRight = !goingRight;
                    hasBeenAtTurningPoint = false;
                    return index++;
                }
                else {
                    hasBeenAtTurningPoint = true;
                    return index;
                }
            }
            else {
                return index--;
            }
        }
    }


    public List<Group> getGroups() {
        return groups;
    }
}

