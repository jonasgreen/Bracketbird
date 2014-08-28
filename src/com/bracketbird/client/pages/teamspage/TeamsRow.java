package com.bracketbird.client.pages.teamspage;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class TeamsRow extends FlowPanel {

    private TextBox seeding;
    private TextBox teamName;


    public TeamsRow() {
        setStyleName("teamsRow");
        add(getSeeding());
        add(getTeamName());

    }

    public TextBox getSeeding() {
        if (seeding == null) {
            seeding = new TextBox();
            seeding.setStyleName("teamsRow_seeding");

        }
        return seeding;
    }

    public TextBox getTeamName() {
        if (teamName == null) {
            teamName = new TextBox();
            teamName.setStyleName("teamsRow_team");
            teamName.getElement().setAttribute("placeholder", "Enter team");
        }
        return teamName;
    }
}
