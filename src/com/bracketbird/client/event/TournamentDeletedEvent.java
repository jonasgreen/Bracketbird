package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 *
 */
public class TournamentDeletedEvent extends GwtEvent {


    public static Type TYPE = new Type();


    public TournamentDeletedEvent() {
    }


    public Type getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(EventHandler handler) {
    	((TournamentDeletedHandler)handler).onChange(this);
    }
}