package com.bracketbird.client.model.tournament;

/**
 *
 */
public abstract class LevelState{

    private final int value;

    public static NotReady notReady = new NotReady(0);
    public static Ready ready = new Ready(1);
    public static InProgress inProgress = new InProgress(2);

    //Only Tournament, stages, groups can be in this one
    public static DonePlaying donePlaying = new DonePlaying(3);
    public static Finished finished = new Finished(4);


    protected LevelState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isNotReady(){
        return false;
    }

    public boolean isReady(){
        return false;
    }

    public boolean isInProgress(){
        return false;
    }

    public boolean isDonePlaying(){
        return false;
    }

    public boolean isFinished(){
        return false;
    }

    public boolean isBelowOrEquals(LevelState state) {
        return this.getValue() <= state.getValue();
    }

    public boolean isBelow(LevelState state) {
        return this.getValue() < state.getValue();
    }

    public abstract void handle(StateCounter col);
}
