package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.model.Team;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class TeamRow extends FlowPanel {

    private SeedingCell seedingCell;
    private TeamCell teamCell;

    private Team team;

    public TeamRow(Team team) {
        this.team = team;
        setStyleName("teamsRow");
        add(getSeedingCell());
        add(getTeamName());
    }

    public SeedingCell getSeedingCell() {
        if (seedingCell == null) {
            seedingCell = new SeedingCell(team);
        }
        return seedingCell;
    }

    public TeamCell getTeamName() {
        if (teamCell == null) {
            teamCell = new TeamCell(team);
        }
        return teamCell;
    }
}
