package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

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
        RTC.getInstance().getTournament().updateMatchField(event);
    }


}
