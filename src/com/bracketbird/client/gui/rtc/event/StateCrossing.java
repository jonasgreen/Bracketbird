package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.tournament.LevelState;

public class StateCrossing {

    LevelState newState;
    LevelState oldState;
    LevelState crossedState;

    public StateCrossing(LevelState oldState, LevelState newState, LevelState crossedState) {
        this.newState = newState;
        this.crossedState = crossedState;
    }

    public boolean fromAbove(){
        return newState.isBelow(crossedState);
    }

    public boolean fromBelow(){
        return newState.isAbove(crossedState);
    }
}
