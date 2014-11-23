package com.bracketbird.client.model.tournament;

/**
 *
 */
public class Ready extends LevelState{

    protected Ready(int value) {
        super(value);
    }

    public void handle(StateCounter col){
        col.count(this);
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
