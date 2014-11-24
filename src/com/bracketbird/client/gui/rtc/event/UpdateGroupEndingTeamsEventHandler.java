package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.Group;

/**
 *
 */
public class UpdateGroupEndingTeamsEventHandler extends REventHandler<UpdateGroupEndingTeamsEvent> {

    public UpdateGroupEndingTeamsEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateGroupEndingTeamsEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void updateTournament(UpdateGroupEndingTeamsEvent event) {
        Group group = RTC.getInstance().getTournament().getGroup(event.getModelId());
        if(group != null){
            group.updateEndingTeams(event.getFinalRank(), event.isFromClient());
        }
    }


}
