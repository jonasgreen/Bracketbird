package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 *
 */
public interface UserStateChangedHandler extends EventHandler {
    public void onChange(UserStateChangedEvent event);
}