package com.bracketbird.client.model.tournament;

/**
 *
 */
public class LevelStateAllMatchesPlayed extends TournamentLevelState{
    private static final long serialVersionUID = -2481868452966103775L;

    protected LevelStateAllMatchesPlayed() {
        super();
    }

    @Override
    public boolean hasStartet() {
        return true;
    }

}
