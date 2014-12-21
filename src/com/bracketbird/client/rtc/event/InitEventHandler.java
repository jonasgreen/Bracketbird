package com.bracketbird.client.rtc.event;


/**
 *
 */
public class InitEventHandler extends REventHandler<InitEvent> {

    public InitEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(InitEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;
    }

    @Override
    protected void updateTournament(InitEvent event) {
        //ignore
    }




}
