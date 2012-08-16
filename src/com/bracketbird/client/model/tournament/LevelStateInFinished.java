package com.bracketbird.client.model.tournament;

/**
 *
 */
public class LevelStateInFinished extends TournamentLevelState{
    private static final long serialVersionUID = 6371328888001323612L;

    protected LevelStateInFinished() {
        super();
    }

    @Override
    public boolean hasStartet() {
        return true;
    }

}
