package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 *
 */
public class TournamentCreatedEvent extends GwtEvent {


    public static Type TYPE = new Type();


    public TournamentCreatedEvent() {
    }


    public Type getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(EventHandler handler) {
    	((TournamentCreatedHandler)handler).onChange(this);
    }
}