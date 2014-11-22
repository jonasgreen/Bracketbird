package com.bracketbird.client.model.tournament;

/**
 *
 */
public enum LevelState{

    notReady(0),
    ready(1),
    inProgress(2),
    donePlaying(3),
    finished(4);

    private final int value;

    private LevelState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isNotReady(){
        return this == notReady;
    }

    public boolean isReady(){
        return this == ready;
    }

    public boolean isInProgress(){
        return this == inProgress;
    }

    public boolean isDonePlaying(){
        return this == donePlaying;
    }

    public boolean isFinished(){
        return this == finished;
    }

    public boolean lowerThan(LevelState state) {
        return this.getValue() < state.getValue();

    }
}
