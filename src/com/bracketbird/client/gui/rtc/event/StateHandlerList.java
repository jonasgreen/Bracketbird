package com.bracketbird.client.gui.rtc.event;


import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class StateHandlerList {

    private String name;

    public StateHandlerList(String name) {
        this.name = name;
    }

    private List<StateHandler> handlers = new ArrayList<StateHandler>();

    public HandlerRegistration addHandler(final StateHandler handler){
        handlers.add(handler);

        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlers.remove(handler);
            }
        };
    }

    public void fireEvent(StateChangedEvent event){
        //createGroupMatch new list to avoid concurrent modification exception
        for (StateHandler l : new ArrayList<StateHandler>(handlers)) {
            l.onChange(event);
        }
    }


}
