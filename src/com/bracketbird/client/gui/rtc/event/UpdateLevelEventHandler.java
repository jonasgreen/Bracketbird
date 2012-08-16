package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class UpdateLevelEventHandler extends REventHandler<UpdateLevelEvent> {

    public UpdateLevelEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateLevelEvent event) {
        return RTC.getInstance().getTournament().getLevel(event.getModelId()).getState().hasStartet();
    }

    @Override
    protected String getWarning() {
        return "Changing the settings of this stage will reset all results in this and any following stages of the tournament. ";  //To change body of implemented methods use File | Settings | File Templates.
    }



    protected void updateTournament(UpdateLevelEvent event) {
        RTC.getInstance().getTournament().updateLevel(event);
    }


}
