package com.bracketbird.client.model.tournament;

/**
 *
 */
public class Finished extends TournamentState{
    private static final long serialVersionUID = 6653089429626326914L;

    public Finished() {
        super("finished");
    }

    @Override
    public boolean isStartet() {
        return true;
    }


}