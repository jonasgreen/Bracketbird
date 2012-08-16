package com.bracketbird.client.model.tournament;


/**
 *
 */
public abstract class TournamentEvent {
    private boolean clientEvent;

    protected TournamentEvent(boolean fromClient) {
        this.clientEvent = fromClient;
    }

    public boolean isClientEvent() {
        return clientEvent;
    }
}