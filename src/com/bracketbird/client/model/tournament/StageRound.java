package com.bracketbird.client.model.tournament;


/**
 *  Represent a round at Stage level - when multiple groups occurs, this will contain matches from
 *  different groups (mix of GroupRounds).
 */
public abstract class StageRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;


    public StageRound(Stage stage, int roundNumber) {
        super(stage, roundNumber);
    }

    @Override
    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        return null;
    }

    
}
