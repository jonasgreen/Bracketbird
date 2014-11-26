package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class UpdateMatchResultEventHandler extends REventHandler<UpdateMatchResultEvent> {

    public UpdateMatchResultEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateMatchResultEvent event) {
        /*Match match = RTC.getInstance().getTournament().findMatch(event.getModelId());
        if(match != null){
            return match.getParent().getStage().getState().isBeyondInProgress();
        }
        */
        return false;
    }

    @Override
    protected String getWarning() {
        return "All matches played. Changing the result will reopen this stage, and reset any following stages";
    }


    protected void updateTournament(UpdateMatchResultEvent event) {
        RTC.getInstance().getTournament().updateMatchResult(event.getModelId(), event.getHomeScores(), event.getOutScores(), event.isFromClient());
    }


}
