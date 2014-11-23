package com.bracketbird.clientcore.model;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.gui.rtc.event.StateHandlerList;
import com.bracketbird.client.model.tournament.HasLevelState;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.StateCounter;
import com.bracketbird.clientcore.model.keys.EntityId;

import java.util.List;

/**
 *
 */
public abstract class PlayableModel<E extends EntityId> extends Model<E> implements HasLevelState{
    private static final long serialVersionUID = -6663910902783234316L;

    public transient StateHandlerList stateHandlers;

    protected LevelState state = LevelState.notReady;

    public PlayableModel(){
        stateHandlers = new StateHandlerList(this.getClass().getSimpleName() + " (stateHandler)");
    }

    public void updateState(boolean fromClient) {
        LevelState newState = calculateState();

        System.out.println(this.getClass().getSimpleName() + ": UPDATE STATE ("+state.getClass().getSimpleName() + " -> "+newState.getClass().getSimpleName()+")");

        if(this.state.equals(newState)){
            System.out.println("STOP");
            return;
        }
        LevelState oldState = this.state;
        this.state = newState;
        stateChanged();
        stateHandlers.fireEvent(new StateChangedEvent(fromClient, oldState, newState));
        if(getParent() != null){
            getParent().updateState(fromClient);
        }
    }

    protected LevelState calculateState(List<? extends HasLevelState> children){
        if (children.isEmpty()) {
            return LevelState.notReady;
        }

        StateCounter c = createStateCounter(children);

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

        if(c.getCountDonePlaying() != 0){
            return LevelState.donePlaying;
        }

        return LevelState.finished;
    }

    public StateCounter createStateCounter(List<? extends HasLevelState> list){
        StateCounter counter = new StateCounter();
        for (HasLevelState item : list) {
            item.getState().handle(counter);
        }
        return counter;
    }


    protected abstract void stateChanged();

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
