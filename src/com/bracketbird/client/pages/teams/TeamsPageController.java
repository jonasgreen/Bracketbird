package com.bracketbird.client.pages.teams;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.client.model.tournament.TournamentTeamEvent;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuImageAndTextComponent;

/**
 *
 */
public class TeamsPageController extends PageController<TeamsPage> {

    private static TeamsPageController instance;


    private TeamsPageController() {

    }

    public static TeamsPageController getInstance() {
        if (instance == null) {
            instance = new TeamsPageController();
        }
        return instance;
    }

    public void afterLoad() {
        getPage().getEnterTeamBox().setFocus(true);
    }

    public TeamsPage newInstance() {
        return new TeamsPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("img/Teams.png", "Add teams");
    }

    private void teamCreated(Team team, boolean isClient) {

    }


    @Override
    public void afterFirstLoad() {
        RTC.getInstance().getTournament().addTeamsListener(new TournamentListener<TournamentTeamEvent>() {

            public void onChange(TournamentTeamEvent event) {
                if (event.getAction() == TournamentTeamEvent.Action.create) {
                    System.out.println("team create");
                    getPage().addTeam(event.getTeam(), event.isClientEvent());
                    //if from server - do sort after eventid. TODO
                }
                else if (event.getAction() == TournamentTeamEvent.Action.delete) {
                    getPage().deleteTeam(event.getTeam(), event.isClientEvent());
                }
            }
        });

        for (Team team : RTC.getInstance().getTournament().getTeams()) {
            getPage().addTeam(team, false);
        }

    }

}