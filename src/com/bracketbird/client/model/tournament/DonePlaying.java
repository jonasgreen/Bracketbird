package com.bracketbird.client.model.tournament;

/**
 *
 */
public class DonePlaying extends LevelState{

    protected DonePlaying(int value) {
        super(value);
    }

    public void handle(StateCounter col){
        col.count(this);
    }

    @Override
    public boolean isDonePlaying() {
        return true;
    }
}
