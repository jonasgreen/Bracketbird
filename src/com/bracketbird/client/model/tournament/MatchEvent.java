package com.bracketbird.client.model.tournament;



/**
 *
 */
public class MatchEvent extends TournamentEvent {

    private Match match;

    public MatchEvent(boolean fromClient, Match m) {
        super(fromClient);
        match = m;
    }

    public Match getMatch() {
        return match;
    }
}