package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class LayoutMatchesEventHandler extends REventHandler<LayoutMatchesEvent> {

    public LayoutMatchesEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(LayoutMatchesEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(LayoutMatchesEvent event) {
        RTC.getInstance().getTournament().layoutMatches(event);
    }


}
