package com.bracketbird.client.model.event;


import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CreateDeleteDispatcher<T> {


    public CreateDeleteDispatcher() {
    }

    private List<CreateDeleteHandler<T>> handlers = new ArrayList<>();

    public HandlerRegistration addHandler(final CreateDeleteHandler<T> handler){
        handlers.add(handler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlers.remove(handler);
            }
        };
    }

    public void fireDeleteEvent(T modelDeleted, boolean fromClient){
        //createGroupMatch new list to avoid concurrent modification exception
        DeleteEvent<T> event = new DeleteEvent<>(fromClient, modelDeleted);
        for (CreateDeleteHandler<T> l : new ArrayList<>(handlers)) {
            l.onDelete(event);
        }
    }

    public void fireCreateEvent(T modelCreated, boolean fromClient){
        //createGroupMatch new list to avoid concurrent modification exception
        CreateEvent<T> event = new CreateEvent<>(fromClient, modelCreated);
        for (CreateDeleteHandler<T> l : new ArrayList<>(handlers)) {
            l.onCreate(event);
        }
    }



}
