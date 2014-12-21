package com.bracketbird.client.model;

import com.bracketbird.client.rtc.event.StateChangedEvent;
import com.bracketbird.client.rtc.event.StateHandler;
import com.bracketbird.client.rtc.event.StateHandlerList;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.keys.EntityId;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 *
 */
public abstract class LevelStateModel<ID extends EntityId> extends Model<ID> implements StateHandler {

    protected LevelState state = LevelState.notReady;
    protected StateHandlerList stateHandlers;

    public LevelStateModel() {
        stateHandlers = new StateHandlerList(this.getClass().getSimpleName());
    }

    public void updateState(boolean fromClient){
        setNewState(calculateState(), fromClient);
    }

    protected void setNewState(LevelState newState, boolean fromClient) {
        LevelState oldState = this.state;
        this.state = newState;

        //Always fire event.
        stateHandlers.fireEvent(new StateChangedEvent(fromClient, oldState, newState));
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

    public HandlerRegistration addStateHandler(StateHandler handler){
        return stateHandlers.addHandler(handler);
    }


}
