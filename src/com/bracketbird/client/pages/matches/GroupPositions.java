package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.StageSettings;
import com.bracketbird.client.model.tournament.Position;
import com.bracketbird.client.model.tournament.RankingSheet;

import java.util.ArrayList;
import java.util.List;

public class GroupPositions {

    private List<Position> positionOfTeams = new ArrayList<Position>();
    private boolean hasTeamsWithSamePosition;
    private Group group;

    public GroupPositions(Group group, StageSettings stageSettings) {
        this.group = group;
        RankingSheet sheet = new RankingSheet(group.getMatches(), stageSettings);
        this.positionOfTeams = sheet.getPositions();

        for (Position position : positionOfTeams) {
            if (position.hasMoreTeams()) {
                hasTeamsWithSamePosition = true;
            }
        }
    }

    public List<Position> getPositionOfTeams() {
        return positionOfTeams;
    }

    public boolean hasTeamsWithSamePosition() {
        return hasTeamsWithSamePosition;
    }

    public Group getGroup() {
        return group;
    }
}
