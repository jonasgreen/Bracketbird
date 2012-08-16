package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;

/**
 *
 */
public class UpdateTeamInfoEventHandler extends REventHandler<UpdateTeamInfoEvent> {

    public UpdateTeamInfoEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateTeamInfoEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(UpdateTeamInfoEvent event) {
        Team team = RTC.getInstance().getTournament().getTeam(event.getModelId());
        if(team == null){
            return;
        }
        team.setInfo(event);
    }


}
