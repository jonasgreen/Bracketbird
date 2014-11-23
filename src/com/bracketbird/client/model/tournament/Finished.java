package com.bracketbird.client.model.tournament;

/**
 *
 */
public class Finished extends LevelState{

    protected Finished(int value) {
        super(value);
    }

    public void handle(StateCounter col){
        col.count(this);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
