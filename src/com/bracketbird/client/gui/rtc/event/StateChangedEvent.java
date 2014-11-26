package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.model.tournament.LevelState;

/**
 *
 */
public class StateChangedEvent {

    private boolean fromClient;

    private LevelState oldState;
    private LevelState newState;

    public StateChangedEvent(boolean fromClient, LevelState oldState, LevelState newState) {
        this.fromClient = fromClient;
        this.oldState = oldState;
        this.newState = newState;
    }

    public boolean isFromClient() {
        return fromClient;
    }

    public LevelState getOldState() {
        return oldState;
    }

    public LevelState getNewState() {
        return newState;
    }

    public StateCrossing crosses(LevelState toBeCrossed){
        return new StateCrossing(oldState, newState, toBeCrossed);
    }
}
