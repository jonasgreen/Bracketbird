package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.gui.rtc.RTC;

/**
 *
 */
public class UpdateSeedingEventHandler extends REventHandler<UpdateSeedingEvent> {

    public UpdateSeedingEventHandler() {
        super();
    }


    @Override
    protected boolean shouldWarn(UpdateSeedingEvent event) {
        return RTC.getInstance().getTournament().getState().isStartet();
    }

    @Override
    protected String getWarning() {
        return "Changing the seeding of the teams will reset all results in the tournament.";
    }



    protected void updateTournament(UpdateSeedingEvent event) {
        RTC.getInstance().getTournament().updateSeeding(event);
    }


}
