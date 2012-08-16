package com.bracketbird.client.model.tournament;

/**
 *
 */
public class LevelStateInProgress extends TournamentLevelState{
    private static final long serialVersionUID = 7476884889163656160L;

    protected LevelStateInProgress() {
        super();
    }

    @Override
    public boolean hasStartet() {
        return true;
    }

}
