package com.bracketbird.client.rtc.event;


import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ModelHandlerList<T> {


    public ModelHandlerList() {
    }

    private List<ModelEventHandler<T>> handlers = new ArrayList<ModelEventHandler<T>>();

    public HandlerRegistration addHandler(final ModelEventHandler<T> handler){
        handlers.add(handler);

        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlers.remove(handler);
            }
        };
    }

    public void fireEvent(ModelEvent<T> event){
        //createGroupMatch new list to avoid concurrent modification exception
        for (ModelEventHandler<T> l : new ArrayList<ModelEventHandler<T>>(handlers)) {
            l.handleEvent(event);
        }
    }



}
