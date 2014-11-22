package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.model.tournament.LevelState;

/**
 *
 */
public class StateChangedEvent extends ModelEvent<LevelState>{

    public StateChangedEvent(boolean fromClient, LevelState before, LevelState after) {
        super(fromClient, before, after);
    }

    @Override
    public boolean isUpdate() {
        return true;
    }

}
