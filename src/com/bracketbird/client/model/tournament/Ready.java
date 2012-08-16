package com.bracketbird.client.model.tournament;

/**
 *
 */
public class Ready extends TournamentState{
    private static final long serialVersionUID = -6716560976240243064L;

    public Ready() {
        super("ready");
    }

    @Override
    public boolean isStartet() {
        return false;
    }
}