package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 *
 */
public class TemplateCreatedEvent extends GwtEvent {


    public static Type TYPE = new Type();


    public TemplateCreatedEvent() {
    }


    public Type getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(EventHandler handler) {
    	((TemplateCreatedHandler)handler).onChange(this);
    }
}