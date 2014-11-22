package com.bracketbird.clientcore.model;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.gui.rtc.event.StateHandlerList;
import com.bracketbird.client.model.tournament.HasLevelState;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.clientcore.model.keys.EntityId;

/**
 *
 */
public abstract class PlayableModel<E extends EntityId> extends Model<E> implements HasLevelState{
    private static final long serialVersionUID = -6663910902783234316L;

    public transient StateHandlerList stateHandlers = new StateHandlerList();

    private LevelState state = LevelState.notReady;

    public PlayableModel(){
    }

    protected void updateState(boolean fromClient) {
        LevelState newState = calculateState();
        if(this.state.equals(newState)){
            return;
        }
        LevelState oldState = this.state;
        this.state = newState;
        stateHandlers.fireEvent(new StateChangedEvent(fromClient, oldState, newState));
        getParent().childHasChangedState(fromClient);
    }

    public boolean isInProgress(){
        return state.isInProgress();
    }

    public boolean isDonePlaying() {
        return state.isDonePlaying();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isReady(){
        return state.isReady();
    }

    public boolean isNotReady(){
        return state.isNotReady();
    }

    public LevelState getState() {
        return state;
    }




}
