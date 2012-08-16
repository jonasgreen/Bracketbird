package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class LevelFinishedEventHandler extends REventHandler<LevelFinishedEvent> {

    public LevelFinishedEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(LevelFinishedEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void updateTournament(LevelFinishedEvent event) {
        RTC.getInstance().getTournament().finishedLevel(event);
    }


}
