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
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(UpdateMatchResultEvent event) {
        RTC.getInstance().getTournament().updateMatchResult(event);
    }


}
