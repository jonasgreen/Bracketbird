package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 *
 */
public interface MatchCreatedOnServerHandler extends EventHandler {
    public void onChange(MatchChangedOnServerEvent event);
}