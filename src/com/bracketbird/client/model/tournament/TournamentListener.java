package com.bracketbird.client.model.tournament;

/**
 *
 */
public interface TournamentListener<K extends TournamentEvent> {
    public void onChange(K event);
}