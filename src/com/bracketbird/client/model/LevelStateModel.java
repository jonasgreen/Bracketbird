package com.bracketbird.client.model;

import com.bracketbird.client.model.event.UpdateDispatcher;
import com.bracketbird.client.model.event.UpdateHandler;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.keys.EntityId;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 *
 */
public abstract class LevelStateModel<ID extends EntityId> extends Model<ID> implements UpdateHandler<LevelState> {

    protected LevelState state = LevelState.notReady;
    protected UpdateDispatcher<LevelState> stateDispatcher = new UpdateDispatcher<>();

    public LevelStateModel() {
    }

    public void updateState(boolean fromClient){
        setNewState(calculateState(), fromClient);
    }

    protected void setNewState(LevelState newState, boolean fromClient) {
        LevelState oldState = this.state;
        this.state = newState;

        //Always fire event.
        stateDispatcher.fireEvent(oldState, newState, fromClient);
    }

    public abstract LevelState calculateState();

    public boolean isInProgress() {
        return state.isInProgress();
    }

    public boolean isDonePlaying() {
        return state.isDonePlaying();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isReady() {
        return state.isReady();
    }

    public boolean isNotReady() {
        return state.isNotReady();
    }

    public LevelState getState() {
        return state;
    }

    public HandlerRegistration addStateHandler(UpdateHandler<LevelState> handler){
        return stateDispatcher.addHandler(handler);
    }


}
