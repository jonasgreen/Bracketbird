package com.bracketbird.client.pages.teamspage;


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
       /* if (getPage().getTeamsTable().isEmpty()) {
            RTC.getInstance().createTeam();
        }
        getPage().setFocus(true);
        */
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
        getPage().addTeam(team, isClient);
    }

    public void beginListening(){
        RTC.getInstance().getTournament().addTeamsListener(new TournamentListener<TournamentTeamEvent>() {

            public void onChange(TournamentTeamEvent event) {
                if (event.getAction() == TournamentTeamEvent.Action.create) {
                    teamCreated(event.getTeam(), event.isClientEvent());
                    //if from server - do sort after eventid. TODO
                }
                else if (event.getAction() == TournamentTeamEvent.Action.delete) {
                    //teamDeleted(event.getTeam(), event.isClientEvent());
                }
            }
        });

        /*RTC.getInstance().getTournament().addSeedingListener(new TournamentListener<SeedingChangedEvent>() {
            public void onChange(SeedingChangedEvent event) {
                seedingChanged();
            }
        });
        */
    }

}