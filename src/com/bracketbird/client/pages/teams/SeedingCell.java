package com.bracketbird.client.pages.teams;

import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class SeedingCell extends Label {

    private Team team;

    public SeedingCell(Team team){
        this.team = team;
        setStyleName("teamsPage_teamsRow_seeding");
        setText("" + (RTC.getInstance().getTournament().getTeams().size()));
        //setEnabled(false);
    }
}
