package com.bracketbird.client.model.tournament;

/**
 *
 */
public class InProgress extends TournamentState{
    private static final long serialVersionUID = 6553672132348882103L;

    public InProgress() {
        super("inprogress");
    }

    @Override
    public boolean isStartet() {
        return true;
    }
}