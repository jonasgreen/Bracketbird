package com.bracketbird.client.model.tournament;

import com.bracketbird.clientcore.model.StateModel;

import java.util.List;

public class LevelStateCalculator {

    protected LevelState stateBasedOnChildren(List<? extends StateModel> children) {
        if (children.isEmpty()) {
            return LevelState.notReady;
        }

        StateCounter c = createStateCounter(children);

        //If a child is in progress - then state is InProgress.
        if (c.getCountInProgress() != 0) {
            return LevelState.inProgress;
        }

        if(c.allIsBelowInProgress()){
            return handleAllIsBelowInProgress(c);
        }
        else if(c.allIsAboveInProgress()){
            return handleAllIsAboveInProgress(c);
        }

        else{//contains above and below in progress - but none in progress
            return LevelState.inProgress;
        }
    }


    private LevelState handleAllIsAboveInProgress(StateCounter c) {
        return c.getCountDonePlaying() != 0 ? LevelState.donePlaying : LevelState.finished;
    }

    private LevelState handleAllIsBelowInProgress(StateCounter c) {
        return c.getCountReady() != 0 ? LevelState.ready : LevelState.notReady;
    }


    public StateCounter createStateCounter(List<? extends StateModel> list) {
        StateCounter counter = new StateCounter();
        for (StateModel item : list) {
            item.getState().handle(counter);
        }
        return counter;
    }

}
