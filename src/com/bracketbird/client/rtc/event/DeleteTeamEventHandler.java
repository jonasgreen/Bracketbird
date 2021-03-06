package com.bracketbird.client.rtc.event;


import com.bracketbird.client.rtc.RTC;

/**
 *
 */
public class DeleteTeamEventHandler extends REventHandler<DeleteTeamEvent> {

    public DeleteTeamEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(DeleteTeamEvent event) {
        return RTC.getInstance().getTournament().getState().isBeyondReady();
    }

    @Override
    protected String getWarning() {
        return "Deleting a new team will reset all results";
    }



    protected void updateTournament(DeleteTeamEvent event) {
        RTC.getInstance().getTournament().deleteTeam(event.getModelId(), event.isFromClient());
    }


}
