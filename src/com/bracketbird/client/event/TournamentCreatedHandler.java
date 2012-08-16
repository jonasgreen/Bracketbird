package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 *
 */
public interface TournamentCreatedHandler extends EventHandler {
    public void onChange(TournamentCreatedEvent event);
}