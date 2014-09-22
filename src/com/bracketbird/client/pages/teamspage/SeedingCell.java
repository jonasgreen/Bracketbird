package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.model.Team;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class SeedingCell extends Label {

    private Team team;

    public SeedingCell(Team team){
        this.team = team;
        setStyleName("teamsRow_seeding");
        setText(""+team.getSeeding());
        //setEnabled(false);
    }
}
