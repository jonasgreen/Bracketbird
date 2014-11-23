package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.model.tournament.LevelState;

/**
 *
 */
public class StateHandlerList extends ModelHandlerList<LevelState>{


    public StateHandlerList(String name) {
        super(name);
    }

    public void fireEvent(StateChangedEvent event){
        super.fireEvent(event);
    }



}
