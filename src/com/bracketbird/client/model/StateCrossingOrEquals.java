package com.bracketbird.client.model;

import com.bracketbird.client.model.tournament.LevelState;

public class StateCrossingOrEquals {

    LevelState newState;
    LevelState oldState;
    LevelState crossedState;

    public StateCrossingOrEquals(LevelState oldState, LevelState newState, LevelState crossedState) {
        this.oldState = oldState;
        this.newState = newState;
        this.crossedState = crossedState;
    }

    public boolean fromAbove(){
        return oldState.isAbove(crossedState) && newState.isBelowOrEquals(crossedState);
    }

    public boolean fromBelow(){
        return oldState.isBelow(crossedState) && newState.isAboveOrEquals(crossedState);
    }
}
