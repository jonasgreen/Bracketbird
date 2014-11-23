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
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    protected void updateTournament(UpdateMatchResultEvent event) {
        Match match = RTC.getInstance().getTournament().findMatch(event.getModelId());
        if(match != null){
            match.updateResult(event.getHomeScores(), event.getOutScores(), event.isFromClient());
        }
    }


}
