package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class CreateStageEventHandler extends REventHandler<CreateLevelEvent> {

    public CreateStageEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(CreateLevelEvent event) {
        return RTC.getInstance().getTournament().getState().isFinished();
    }

    @Override
    protected String getWarning() {
        return "Tournament is updateEndingTeams. Adding a new State will reopen it";
    }



    protected void updateTournament(CreateLevelEvent event) {
        RTC.getInstance().getTournament().createStage(event.getStageType(), event.getModelId(), event.getEventId(), event.isFromClient());
    }


}
