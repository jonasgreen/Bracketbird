package com.bracketbird.client.pages.teams;


import com.bracketbird.client.model.event.CreateDeleteHandler;
import com.bracketbird.client.model.event.CreateEvent;
import com.bracketbird.client.model.event.DeleteEvent;
import com.bracketbird.client.rtc.RTC;
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

    @Override
    public void afterLoad() {
        getPage().getEnterTeamBox().setFocus(true);
    }

    public TeamsPage newInstance() {
        return new TeamsPage();
    }


    @Override
    public void afterFirstLoad() {

        RTC.getInstance().getTournament().teamsDispatcher.addHandler(new CreateDeleteHandler<Team>() {
            @Override
            public void onCreate(CreateEvent<Team> event) {
                getPage().addTeam(event.getValue(), event.isFromClient());
            }

            @Override
            public void onDelete(DeleteEvent<Team> event) {
                getPage().deleteTeam(event.getValue(), event.isFromClient());
            }
        });


        for (Team team : RTC.getInstance().getTournament().getTeams()) {
            getPage().addTeam(team, false);
        }

    }

}