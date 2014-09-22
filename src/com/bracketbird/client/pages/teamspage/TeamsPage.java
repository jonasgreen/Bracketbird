package com.bracketbird.client.pages.teamspage;


import com.bracketbird.client.model.Team;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.appcontrol.TournamentContext;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TeamsPage extends Page<TeamsPageController> {

    private static int ROW_HEIGHT = 30;
    private static int TOP_START = 40;

    private static int TOP_START_ENTER_TEAM = TOP_START + (6 * ROW_HEIGHT);


    private List<TeamRow> teamRows;
    private EnterTeam enterTeamBox;


    public void init() {
        setStyleName("teamsPage");
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
        int index = 0;
        for (TeamRow r : teamRows) {
            setTop(r, top);
            top += ROW_HEIGHT;
            r.getSeedingCell().setText(++index + "");
        }

        int topOfEnterTeamsBox = getTopOfEnterTeamsBox();
        setTop(getEnterTeamBox(), topOfEnterTeamsBox);
        setHeight((topOfEnterTeamsBox + ROW_HEIGHT) + "px");
        TournamentContext.get().updateMenuShadow();
    }

    private int getTopOfEnterTeamsBox() {
        int newTopOfEnterTeam = getTopOfNextTeam() + ROW_HEIGHT;
        return newTopOfEnterTeam <= TOP_START_ENTER_TEAM ? TOP_START_ENTER_TEAM : newTopOfEnterTeam;
    }

    private void setTop(Widget w, int top) {
        w.getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public void addTeam(Team team, boolean isClient) {
        TeamRow row = createNewTeamRow(team);
        if (isClient) {
            //add on top of enterTeamBox, delete text in enterTeamsBox and recalculate positions
            add(row);
            setTop(row, getTopOfEnterTeamsBox());
        }
        else {
            //add below last item and recalculate positions
            setTop(row, getTopOfNextTeam());
            add(row);
        }
        teamRows.add(row);

        Timer timer = new Timer() {
            @Override
            public void run() {

                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        updatePositions();
                        getEnterTeamBox().setText("");
                        TournamentContext.get().getPageContainer().ensureVisible(getEnterTeamBox());
                    }
                });
            }
        };


        timer.schedule(100);
    }

    private TeamRow createNewTeamRow(Team team) {
        final TeamRow row = new TeamRow(team);
        row.addDomHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if(KeyCodes.KEY_TAB == event.getNativeKeyCode() && teamRows.indexOf(row) == teamRows.size()-1){
                    event.stopPropagation();
                    event.preventDefault();
                    getEnterTeamBox().setFocus(true);
                }
            }
        }, KeyDownEvent.getType());
        return row;
    }

    public void deleteTeam(Team team, boolean clientEvent) {
        int index = 0;
        for (TeamRow t : teamRows) {
            if (t.getTeam().equals(team)) {
                t.removeFromParent();
                break;
            }
            index++;
        }
        teamRows.remove(index);

        if (clientEvent) {
            if (teamRows.isEmpty()) {
                getEnterTeamBox().setFocus(true);
            }
            else if (teamRows.size() > index) {
                teamRows.get(index).getTeamName().setFocus(true);
            }
            else {
                teamRows.get(index - 1).getTeamName().setFocus(true);
            }
        }

        updatePositions();
    }

    private int getTopOfNextTeam() {
        return TOP_START + ((getTeamRows().size() * ROW_HEIGHT));
    }


    public void moveFocusUp(TeamRow comingFromRow) {
        int index = teamRows.indexOf(comingFromRow);
        if (index == -1 || index == 0) {
            return;
        }
        teamRows.get(index - 1).getTeamName().setFocus(true);
    }

    public void moveFocusDown(TeamRow comingFromRow) {
        int index = teamRows.indexOf(comingFromRow);
        if (index == -1) {
            return;
        }

        if (index < teamRows.size() - 1) {
            teamRows.get(index + 1).getTeamName().setFocus(true);
        }
        else {
            getEnterTeamBox().setFocus(true);
        }
    }
}