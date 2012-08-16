package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupBuilder {

    private GroupName groupNames = new GroupName();
    private List<AGroup> groups = new ArrayList<AGroup>();
    private List<Team> teams;

    private int index = 0;
    private boolean goingRight = false;
    private boolean hasBeenAtTurningPoint = true;

    public GroupBuilder(int numberOfGroups, List<Team> teams) {
        super();
        this.teams = teams;
        int i = 0;
        while (i < numberOfGroups) {
            groups.add(new AGroup(groupNames.next()));
            i++;
        }
        buildGroups();
    }


    private void buildGroups() {
        //added to the same group
        if(groups.size() == 1){
            AGroup aGroup = groups.get(0);
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


    public List<AGroup> getGroups() {
        return groups;
    }
}

