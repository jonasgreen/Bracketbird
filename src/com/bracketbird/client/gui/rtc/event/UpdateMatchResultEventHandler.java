package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.Match;

/**
 *
 */
public class UpdateMatchResultEventHandler extends REventHandler<UpdateMatchResultEvent> {

    public UpdateMatchResultEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateMatchResultEvent event) {
        Match match = RTC.getInstance().getTournament().findMatch(event.getModelId());
        if(match != null){
            return match.getParent().getStage().getState().isBeyondReady();
        }
        return false;
    }

    @Override
    protected String getWarning() {
        return "Stage is updateEndingTeams. Changing the result will reopen this stage, and reset any following stages";
    }


    protected void updateTournament(UpdateMatchResultEvent event) {
        RTC.getInstance().getTournament().updateMatchResult(event.getModelId(), event.getHomeScores(), event.getOutScores(), event.isFromClient());
    }


}
