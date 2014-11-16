package com.bracketbird.client.pages.teams;


import com.bracketbird.client.model.Team;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.appcontrol.TournamentContext;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TeamsPage extends Page<TeamsPageController> {

    private static int ROW_HEIGHT = 30;
    private static int ROW_WIDTH = 320;

    private static int TOP_START = 40;

    private static int TOP_START_ENTER_TEAM = TOP_START + (6 * ROW_HEIGHT);


    private List<TeamRow> teamRows = new ArrayList<TeamRow>();
    private EnterTeam enterTeamBox;
    private boolean hasTwoColumns = false;


    public void init() {
        setStyleName("teamsPage");
        //teams are added/removed by controller listening for create/delete team events.
        add(getEnterTeamBox());
        updatePositions();
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                updatePositions();
            }
        });
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
            enterTeamBox.setTop(TOP_START_ENTER_TEAM);
        }
        return enterTeamBox;
    }

    public void updatePositions() {

        boolean buildTwoColumns = shouldBuildTwoColumns();
        int scrollPanelHeight = TournamentContext.get().getPageContainer().getOffsetHeight();

        int index = 0;
        int indexInColumn = 0;

        int top = TOP_START;
        int topOfEnterTeamsBox = TOP_START_ENTER_TEAM;

        int left = 0;

        for (TeamRow r : teamRows) {
            r.getSeedingCell().setText(++index + "");

            setTop(r, top);
            setLeft(r, left);

            top += ROW_HEIGHT;
            topOfEnterTeamsBox = top+ROW_HEIGHT > topOfEnterTeamsBox ? top+ROW_HEIGHT : topOfEnterTeamsBox;
            getEnterTeamBox().setTop(topOfEnterTeamsBox);
            getEnterTeamBox().setLeft(left);

            indexInColumn++;
            if(buildTwoColumns && calculateColumnHeight(indexInColumn+1) > scrollPanelHeight){
                top = TOP_START;
                left = ROW_WIDTH + 20;
                indexInColumn = 0;
            }
        }

        setHeight((topOfEnterTeamsBox + ROW_HEIGHT) + "px");
        TournamentContext.get().updateMenuShadow();

        if(hasTwoColumns != buildTwoColumns){
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    TournamentContext.get().getPageContainer().scrollToBottom();
                }
            });
        }
        hasTwoColumns = buildTwoColumns;
    }

    private boolean shouldBuildTwoColumns(){
        if(teamRows.size() == 0){
            return false;
        }
        int rowsInColumnTwo = (teamRows.size()/2) + (teamRows.size()%2);
        int scrollPanelHeight = TournamentContext.get().getPageContainer().getOffsetHeight();

        boolean moreThanOneColumn = calculateColumnHeight(teamRows.size()) > scrollPanelHeight;
        boolean noMoreThanTwoColumn = calculateColumnHeight(rowsInColumnTwo) <= scrollPanelHeight;

        return moreThanOneColumn && noMoreThanTwoColumn;
    }

    private int calculateColumnHeight(int columns) {
        return (columns * ROW_HEIGHT) + 150;
    }

    private void setTop(Widget w, int top) {
        w.getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    private void setLeft(Widget w, int left) {
        w.getElement().getStyle().setLeft(left, Style.Unit.PX);
    }

    public void addTeam(Team team, boolean isClient) {
        TeamRow row = createNewTeamRow(team);
        if (isClient) {
            //add on top of enterTeamBox, delete text in enterTeamsBox and recalculate positions
            add(row);
            setTop(row, getEnterTeamBox().getTop());
            setLeft(row, getEnterTeamBox().getLeft());
        }
        else {
            //add below last item and recalculate positions
            setTop(row, (TournamentContext.get().getPageContainer().getOffsetHeight()/2));
            setLeft(row, Window.getClientWidth());

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