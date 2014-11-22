package com.bracketbird.client.model.tournament;

public interface HasLevelState {

    public <T extends HasLevelState> HasLevelState getParent();

    public LevelState getState();
    public LevelState calculateState();
    public void childHasChangedState(boolean fromClient);

    public boolean isNotReady();
    public boolean isReady();
    public boolean isInProgress();
    public boolean isDonePlaying();
    public boolean isFinished();


}
