package com.bracketbird.client.model.event;


import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UpdateDispatcher<T> {


    public UpdateDispatcher() {
    }

    private List<UpdateHandler<T>> handlers = new ArrayList<>();

    public HandlerRegistration addHandler(final UpdateHandler<T> handler){
        handlers.add(handler);

        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlers.remove(handler);
            }
        };
    }

    public void fireEvent(T oldValue, T newValue, boolean fromClient){
        UpdateEvent<T> event = new UpdateEvent<>(fromClient, oldValue, newValue);
        //createGroupMatch new list to avoid concurrent modification exception
        for (UpdateHandler<T> l : new ArrayList<>(handlers)) {
            l.onUpdate(event);
        }
    }



}
