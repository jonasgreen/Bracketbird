package com.bracketbird.client.pages.teams;


import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.rtc.event.ModelEvent;
import com.bracketbird.client.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.appcontrol.PageController;

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


    @Override
    public void afterFirstLoad() {

        RTC.getInstance().getTournament().teamsEventHandlers.addHandler(new ModelEventHandler<Team>() {
            @Override
            public void handleEvent(ModelEvent<Team> event) {
                if (event.isCreate()) {
                    getPage().addTeam(event.getNewValue(), event.isFromClient());
                }
                else if (event.isDelete()) {
                    getPage().deleteTeam(event.getOldValue(), event.isFromClient());
                }
            }
        });


        for (Team team : RTC.getInstance().getTournament().getTeams()) {
            getPage().addTeam(team, false);
        }

    }

}