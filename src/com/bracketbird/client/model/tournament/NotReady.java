package com.bracketbird.client.model.tournament;

/**
 *
 */
public class NotReady extends LevelState{

    protected NotReady(int value) {
        super(value);
    }

    public void handle(StateCounter col){
        col.count(this);
    }

    @Override
    public boolean isNotReady() {
        return true;
    }
}
