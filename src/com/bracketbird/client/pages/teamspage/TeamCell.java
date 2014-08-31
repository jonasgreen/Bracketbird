package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.model.Team;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class TeamCell extends TextBox{

    private Team team;


    public TeamCell(Team team) {
        this.team = team;

        setStyleName("teamsRow_team");
        setText(team.getName());

        addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                keyDown(event);
            }
        });

        addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                keyUp(event);
            }
        });
    }

    private void keyUp(KeyUpEvent event) {
    }

    private void keyDown(KeyDownEvent event) {

    }
}
