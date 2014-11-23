package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.Match;

/**
 *
 */
public class UpdateMatchFieldEventHandler extends REventHandler<UpdateMatchFieldEvent> {

    public UpdateMatchFieldEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateMatchFieldEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(UpdateMatchFieldEvent event) {
        Match match = RTC.getInstance().getTournament().findMatch(event.getModelId());
        if(match != null){
            match.updateField(event.getField(), event.isFromClient());
        }
    }


}
