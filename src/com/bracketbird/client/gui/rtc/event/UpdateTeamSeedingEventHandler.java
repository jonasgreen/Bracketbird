package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;

/**
 *
 */
public class UpdateTeamSeedingEventHandler extends REventHandler<UpdateTeamSeedingEvent> {

    public UpdateTeamSeedingEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateTeamSeedingEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return "";
    }



    protected void updateTournament(UpdateTeamSeedingEvent event) {
        Team team = RTC.getInstance().getTournament().getTeam(event.getModelId());
        if(team != null){
            team.updateSeeding(event.getSeeding(), event.isFromClient());
        }
    }


}
