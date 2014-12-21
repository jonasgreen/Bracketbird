package com.bracketbird.client.rtc.event;


import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.tournament.Tournament;

/**
 *
 */
public class UpdateStageEventHandler extends REventHandler<UpdateStageEvent> {

    public UpdateStageEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateStageEvent event) {
        return RTC.getInstance().getTournament().getLevel(event.getModelId()).isInProgress();
    }

    @Override
    protected String getWarning() {
        return "Changing the settings of this stage will reset all results in this and any following stages of the tournament. ";  //To change body of implemented methods use File | Settings | File Templates.
    }


    protected void updateTournament(UpdateStageEvent event) {
        Tournament tournament = RTC.getInstance().getTournament();
        tournament.updateStage(event.getModelId(), event.getStageSettings(), event.isFromClient());
    }


}
