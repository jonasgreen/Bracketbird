package com.bracketbird.client.gui.rtc.event;


import com.bracketbird.client.model.tournament.LevelState;

/**
 *
 */
public class StateHandlerList extends ModelHandlerList<LevelState>{


    public void fireEvent(StateChangedEvent event){
        super.fireEvent(event);
    }



}
