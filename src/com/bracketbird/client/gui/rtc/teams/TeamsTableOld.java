package com.bracketbird.client.gui.rtc.teams;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.tournament.SeedingChangedEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.client.model.tournament.TournamentTeamEvent;
import com.bracketbird.client.table.Table;
import com.bracketbird.client.table.TableManager;
import com.bracketbird.client.table.TableRow;
import com.google.gwt.user.client.Timer;

/**
 *
 */
public class TeamsTableOld extends Table {

    public TeamsTableOld(TableManager tm) {
        super(tm);
        //addHeaderRow(new TeamHeaderRow(this));

        RTC.getInstance().getTournament().addTeamsListener(new TournamentListener<TournamentTeamEvent>() {

            public void onChange(TournamentTeamEvent event) {
                System.out.println("teamsTable");
                if (event.getAction() == TournamentTeamEvent.Action.create) {
                    teamCreated(event.getTeam(), event.isClientEvent());
                    //if from server - do sort after eventid. TODO
                }
                else if (event.getAction() == TournamentTeamEvent.Action.delete) {
                    teamDeleted(event.getTeam(), event.isClientEvent());
                }
            }
        });

        RTC.getInstance().getTournament().addSeedingListener(new TournamentListener<SeedingChangedEvent>() {
            public void onChange(SeedingChangedEvent event) {
                seedingChanged();
            }
        });
    }

    private void seedingChanged() {
        removeAllRows();
        for (Team team : RTC.getInstance().getTournament().getTeams()) {
            teamCreated(team, false);
        }
    }

    private void removeAllRows() {
        while (!getRows().isEmpty()){
            deleteRow(0);
        }
    }


    private void updateSeedingOnCreate() {
        RTC.getInstance().getTournament().getNextSeeding();
    }

    @Override
    public void backspace(TableRow row, int cellIndex) {
        if (cellIndex == 1 && (getRows().size() > 1)) {
            RTC.getInstance().deleteTeam(((TeamsTableRowOld) row).getTeam().getId());
        }
    }

    @Override
    public void enter(TableRow row, int cellIndex) {
        if (isLastRow(getRows().indexOf(row))) {
            RTC.getInstance().createTeam("", 0);
        }
        else {
            down(row, cellIndex);
        }
    }

    public void teamDeleted(Team team, boolean isClientEvent) {
        int index = 0;
        for (TableRow row : getRows()) {
            if (((TeamsTableRowOld) row).getTeam().equals(team)) {
                deleteRow(index);
                break;
            }
            index++;
        }
        final int newIndex = index;
        if (isClientEvent) {
            Timer t = new Timer() {
                @Override
                public void run() {
                    if (getRows().size() > newIndex) {
                        getRows().get(newIndex).setFocus(true);
                    }
                    else if (!getRows().isEmpty()) {
                        getRows().get(newIndex - 1).setFocus(true);
                    }
                }
            };
            t.schedule(150);
        }
    }


    private void teamCreated(Team team, boolean isClient) {
        TeamsTableRowOld teamsTableRow = new TeamsTableRowOld(this, team);
        addTableRow(teamsTableRow);
        if (isClient) {
            teamsTableRow.setFocus(true);
        }
    }

    public void deleteLastFocusedTeam() {
        if (getRows().size() == 1) {
            return;
        }
        if (getLastFocus() != null && ((TeamsTableRowOld) getLastFocus()).getLostFocusTime() + 1000 > System.currentTimeMillis()) {
            TeamId id = ((TeamsTableRowOld) getLastFocus()).getTeam().getId();
            setLastFocus(null);
            RTC.getInstance().deleteTeam(id);
        }
    }




    public Team getTeam(TeamId modelId) {
        for (TableRow tableRow : getRows()) {
            Team team = ((TeamsTableRowOld) tableRow).getTeam();
            if (team.getId().equals(modelId)) {
                return team;
            }
        }
        return null;
    }


    public void setFocus(boolean focus) {
        if (getRows() == null || getRows().isEmpty()) {
            return;
        }
        if (lastFocus != null) {
            lastFocus.setFocus(true);
        }
        else {
            setFocus(0, 1);
        }
    }
}
