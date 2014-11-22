package com.bracketbird.client.pages.teams;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.Team;
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

        RTC.getInstance().getTournament().teamsEventHandlers.addHandler(new ModelEventHandler<Team>() {
            @Override
            public void handleEvent(ModelEvent<Team> event) {
                if (event.isCreate()) {
                    getPage().addTeam(event.getAfter(), event.isFromClient());
                }
                else if (event.isDelete()) {
                    getPage().deleteTeam(event.getBefore(), event.isFromClient());
                }
            }
        });


        for (Team team : RTC.getInstance().getTournament().getTeams()) {
            getPage().addTeam(team, false);
        }

    }

}