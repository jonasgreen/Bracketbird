package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.model.Team;

/**
 *
 */
public class CreateTeamEventHandler extends REventHandler<CreateTeamEvent> {

    public CreateTeamEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(CreateTeamEvent event) {
        return RTC.getInstance().getTournament().getState().isStartet();
    }

    @Override
    protected String getWarning() {
        return "Adding a new team will reset all results";
    }



    protected void updateTournament(CreateTeamEvent event) {
        RTC.getInstance().getTournament().createTeam(event);
    }


    public void postServerAction(final CreateTeamEvent event){
        //get teams page and sort teams.

                Team team = TeamsPageController.getInstance().getPage().getTeamsTable().getTeam(event.getModelId());
                team.setEventLogId(event.getEventId());



        //istedeet for at sortere tabellen - kig på nyoprettet row og se den skal flyttes op eller ned.
        // Skal også gøres når der oprettes - pga server event (anden klient).

    }


}
