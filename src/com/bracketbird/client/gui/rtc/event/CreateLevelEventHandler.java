package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class CreateLevelEventHandler extends REventHandler<CreateLevelEvent> {

    public CreateLevelEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(CreateLevelEvent event) {
        return false;
    }

    @Override
    protected String getWarning() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(CreateLevelEvent event) {
        RTC.getInstance().getTournament().createLevel(event.getStageType(), event.getModelId(), event.getEventId(), event.isFromClient());
    }


}
