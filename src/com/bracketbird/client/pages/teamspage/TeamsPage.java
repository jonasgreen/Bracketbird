package com.bracketbird.client.pages.teamspage;


import com.bracketbird.client.model.Team;
import com.bracketbird.clientcore.appcontrol.ScrollPanelPage;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TeamsPage extends ScrollPanelPage<TeamsPageController> {

    private static int ROW_HEIGHT = 30;
    private static int TOP_START = 50;

    private static int TOP_START_ENTER_TEAM = TOP_START + (6*ROW_HEIGHT);


    private List<TeamRow> teamRows;
    private EnterTeam enterTeamBox;


    public void init() {
        //teams are added/removed by controller listening for create/delete team events.
        add(getEnterTeamBox());
        updatePositions();
    }

    public List<TeamRow> getTeamRows() {
        if (teamRows == null) {
            teamRows = new ArrayList<TeamRow>();
        }
        return teamRows;
    }

    public EnterTeam getEnterTeamBox() {
        if (enterTeamBox == null) {
            enterTeamBox = new EnterTeam(this);
            setTop(enterTeamBox, (getTopOfEnterTeamsBox()));
        }
        return enterTeamBox;
    }

    public void updatePositions() {
        int top = TOP_START;
        for (TeamRow r : teamRows) {
            setTop(r, top);
            top += ROW_HEIGHT;
        }

        int topOfEnterTeamsBox = getTopOfEnterTeamsBox();
        setTop(getEnterTeamBox(), topOfEnterTeamsBox);
        getScrollPanelContent().setHeight((topOfEnterTeamsBox + ROW_HEIGHT) + "px");
    }

    private int getTopOfEnterTeamsBox() {
        int newTopOfEnterTeam = getTopOfNextTeam() + ROW_HEIGHT;
        return newTopOfEnterTeam <= TOP_START_ENTER_TEAM ? TOP_START_ENTER_TEAM : newTopOfEnterTeam;
    }

    private void setTop(Widget w, int top) {
        w.getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public void addTeam(Team team, boolean isClient) {
        final TeamRow row = new TeamRow(team);
        if (!isClient) {
            //add below last item and recalculate positions
            setTop(row, getTopOfNextTeam());
            add(row);
        }
        else {
            //add on top of enterTeamBox, delete text in enterTeamsBox and recalculate positions
            add(row);
            setTop(row, getTopOfEnterTeamsBox());
        }
        teamRows.add(row);

        Timer timer = new Timer() {
            @Override
            public void run() {
                int m = getContentPanel().getMaximumVerticalScrollPosition();
                getContentPanel().setVerticalScrollPosition(m);

                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        updatePositions();
                        getEnterTeamBox().setText("");
                        row.getSeedingCell().getElement().getStyle().setOpacity(0.5);
                    }
                });
            }
        };


        timer.schedule(100);


    }

    private int getTopOfNextTeam() {
        return TOP_START + ((getTeamRows().size() * ROW_HEIGHT));
    }


}