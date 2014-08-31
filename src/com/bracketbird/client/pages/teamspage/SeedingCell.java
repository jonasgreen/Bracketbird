package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.model.Team;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class SeedingCell extends TextBox{

    private Team team;

    public SeedingCell(Team team){
        this.team = team;
        setStyleName("teamsRow_seeding");
        setText(""+team.getSeeding());
    }
}
