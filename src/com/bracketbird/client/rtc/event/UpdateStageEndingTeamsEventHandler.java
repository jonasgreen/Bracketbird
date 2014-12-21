package com.bracketbird.client.rtc.event;


import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.tournament.Stage;

/**
 *
 */
public class UpdateStageEndingTeamsEventHandler extends REventHandler<UpdateStageEndingTeamsEvent> {

    public UpdateStageEndingTeamsEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateStageEndingTeamsEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void updateTournament(UpdateStageEndingTeamsEvent event) {
        Stage stage = RTC.getInstance().getTournament().getStage(event.getModelId());
        if(stage != null){
            stage.updateEndingTeams(event.getFinalRank(), event.isFromClient());
        }
    }


}
