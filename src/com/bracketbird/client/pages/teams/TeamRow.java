package com.bracketbird.client.pages.teams;

import com.bracketbird.client.Css;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class TeamRow extends FlowPanel {

    private FlowPanel deleteIcon;
    private SeedingCell seedingCell;
    private TeamCell teamCell;

    private Team team;

    public TeamRow(Team team) {
        this.team = team;
        Css.style(this, "teamsRow", "flex_alignItems_center");
        add(getSeedingCell());
        add(getDeleteIcon());
        add(getTeamName());


        //to avoid icon showing up when team row is just added and animation is going on
        Timer t = new Timer() {
            @Override
            public void run() {
                addDomHandler(new MouseOverHandler() {
                    @Override
                    public void onMouseOver(MouseOverEvent event) {
                        getDeleteIcon().setVisible(true);
                    }
                }, MouseOverEvent.getType());

                addDomHandler(new MouseOutHandler() {
                    @Override
                    public void onMouseOut(MouseOutEvent event) {
                        getDeleteIcon().setVisible(false);
                    }
                }, MouseOutEvent.getType());
            }
        };

        t.schedule(500);
    }


    public FlowPanel getDeleteIcon() {
        if (deleteIcon == null) {
            deleteIcon = new FlowPanel();
            deleteIcon.setStyleName("icon-uniE600");
            deleteIcon.addStyleName("teamsRow_deleteIcon");
            deleteIcon.setVisible(false);
            deleteIcon.setTitle("Delete team");
            deleteIcon.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    RTC.getInstance().deleteTeam(team.getId());
                }
            }, ClickEvent.getType());
        }
        return deleteIcon;
    }

    public SeedingCell getSeedingCell() {
        if (seedingCell == null) {
            seedingCell = new SeedingCell(team);
            //seedingCell.setTitle("Change seeding");
        }
        return seedingCell;
    }

    public TeamCell getTeamName() {
        if (teamCell == null) {
            teamCell = new TeamCell(this, team);
        }
        return teamCell;
    }

    public Team getTeam() {
        return team;
    }
}
