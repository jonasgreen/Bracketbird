package com.bracketbird.client.model.tournament;

/**
 *
 */
public class InProgress extends LevelState{

    protected InProgress(int value) {
        super(value);
    }

    public void handle(StateCounter col){
        col.count(this);
    }

    @Override
    public boolean isInProgress() {
        return true;
    }
}
