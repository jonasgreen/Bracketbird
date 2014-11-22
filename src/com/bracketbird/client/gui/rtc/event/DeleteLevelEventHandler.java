package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class DeleteLevelEventHandler extends REventHandler<DeleteLevelEvent> {

    public DeleteLevelEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(DeleteLevelEvent event) {
        return RTC.getInstance().getTournament().getLevel(event.getModelId()).isInProgress();
    }



    @Override
    protected String getWarning() {
        return "Deleting this stage will reset all results in this and any following stages of the tournament. ";  //To change body of implemented methods use File | Settings | File Templates.
    }




    protected void updateTournament(DeleteLevelEvent event) {
        RTC.getInstance().getTournament().deleteLevel(event.getModelId(), event.isFromClient());
    }


}
