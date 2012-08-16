package com.bracketbird.client.model.tournament;

import java.io.Serializable;

/**
 *
 */
public class LevelStateEmpty extends TournamentLevelState{
    private static final long serialVersionUID = 1649049008905125302L;

    protected LevelStateEmpty() {
        super();
    }

    @Override
    public boolean hasStartet() {
        return false;
    }

}
