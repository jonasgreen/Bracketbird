package com.bracketbird.client.gui.rtc.event;


import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ModelHandlerList<T> {

    private List<ModelEventHandler<T>> listeners = new ArrayList<ModelEventHandler<T>>();


    public HandlerRegistration addHandler(final ModelEventHandler<T> handler){
        listeners.add(handler);

        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                listeners.remove(handler);
            }
        };
    }

    public void fireEvent(ModelEvent<T> event){
        //createGroupMatch new list to avoid concurrent modification exception
        for (ModelEventHandler<T> l : new ArrayList<ModelEventHandler<T>>(listeners)) {
            l.handleEvent(event);
        }
    }



}
