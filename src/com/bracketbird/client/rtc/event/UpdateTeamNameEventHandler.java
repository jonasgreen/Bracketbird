package com.bracketbird.client.rtc.event;


import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.Team;

/**
 *
 */
public class UpdateTeamNameEventHandler extends REventHandler<UpdateTeamNameEvent> {

    public UpdateTeamNameEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateTeamNameEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(UpdateTeamNameEvent event) {
        Team team = RTC.getInstance().getTournament().getTeam(event.getModelId());
        if(team != null){
            team.updateName(event.getName(), event.isFromClient());
        }
    }


}
