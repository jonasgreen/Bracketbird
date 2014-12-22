package com.bracketbird.client.model;

import com.bracketbird.client.model.tournament.LevelState;

public class StateCrossing {

    LevelState newState;
    LevelState oldState;
    LevelState crossedState;

    public StateCrossing(LevelState oldState, LevelState newState, LevelState crossedState) {
        this.oldState = oldState;
        this.newState = newState;
        this.crossedState = crossedState;
    }

    public boolean fromAbove(){
        return oldState.isAbove(crossedState) && newState.isBelow(crossedState);
    }

    public boolean fromBelow(){
        return oldState.isBelow(crossedState) && newState.isAbove(crossedState);
    }
}
