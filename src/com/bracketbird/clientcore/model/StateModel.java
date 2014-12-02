package com.bracketbird.clientcore.model;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.gui.rtc.event.StateHandler;
import com.bracketbird.client.gui.rtc.event.StateHandlerList;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.StateCounter;
import com.bracketbird.clientcore.model.keys.EntityId;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.List;

/**
 *
 */
public abstract class StateModel<ID extends EntityId> extends Model<ID> implements StateHandler {
    private static final long serialVersionUID = -6663910902783234316L;

    protected LevelState state = LevelState.notReady;
    protected StateHandlerList stateHandlers;

    public StateModel() {
        stateHandlers = new StateHandlerList(this.getClass().getSimpleName());
    }

    public void updateState(boolean fromClient) {
        LevelState newState = calculateState();

        System.out.println(this.getClass().getSimpleName() + ": UPDATE STATE (" + state.getClass().getSimpleName() + " -> " + newState.getClass().getSimpleName() + ")");

        if (shouldTriggerStateEvent(this.state, newState)) {
            LevelState oldState = this.state;
            this.state = stateChanged(oldState, newState);
            stateHandlers.fireEvent(new StateChangedEvent(fromClient, oldState, newState));

        }
        else {
            System.out.println("STOP");
        }
    }

    protected abstract LevelState calculateState();

    protected void setNewState(LevelState newState, boolean fromClient) {
        LevelState oldState = this.state;
        this.state = newState;
        stateHandlers.fireEvent(new StateChangedEvent(fromClient, oldState, newState));
    }

    protected boolean shouldTriggerStateEvent(LevelState oldState, LevelState newState) {
        return oldState.in(LevelState.donePlaying, LevelState.finished) || !oldState.equals(newState);
    }

    protected LevelState stateBasedOnChildren(List<? extends StateModel> children) {
        if (children.isEmpty()) {
            return LevelState.notReady;
        }

        StateCounter c = createStateCounter(children);
        if (c.allIsNotReady(children)) {
            return LevelState.notReady;
        }

        if (c.allIsReady(children)) {
            return LevelState.ready;
        }

        //If a child is in progress - then state is InProgress.
        if (c.getCountInProgress() != 0) {
            return LevelState.inProgress;
        }

        //if one child is below InProgress - InProgress is the highest state possible
        if (c.getCountNotReady() != 0 || c.getCountReady() != 0) {
            if (c.getCountFinished() != 0 || c.getCountDonePlaying() != 0) {
                return LevelState.inProgress;
            }
            return LevelState.ready;
        }

        if (c.getCountDonePlaying() != 0) {
            return LevelState.donePlaying;
        }

        return LevelState.finished;
    }

    public StateCounter createStateCounter(List<? extends StateModel> list) {
        StateCounter counter = new StateCounter();
        for (StateModel item : list) {
            item.getState().handle(counter);
        }
        return counter;
    }


    protected abstract LevelState stateChanged(LevelState oldState, LevelState newState);

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
