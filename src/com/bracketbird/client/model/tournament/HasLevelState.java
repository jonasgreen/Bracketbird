package com.bracketbird.client.model.tournament;

public interface HasLevelState {

    public <T extends HasLevelState> HasLevelState getParent();

    public LevelState getState();
    public void updateState(boolean fromClient);
    public LevelState calculateState();

    public boolean isNotReady();
    public boolean isReady();
    public boolean isInProgress();
    public boolean isDonePlaying();
    public boolean isFinished();


}
