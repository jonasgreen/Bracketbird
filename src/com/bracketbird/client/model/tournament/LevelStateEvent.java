package com.bracketbird.client.model.tournament;


/**
 *
 */
public class LevelStateEvent extends TournamentEvent {

    private TournamentLevelState oldState;
    private TournamentLevelState newState;
    private TournamentLevel level;

    public LevelStateEvent(TournamentLevel level, TournamentLevelState oldState, TournamentLevelState newState, boolean fromClient) {
        super(fromClient);
        this.level = level;
        this.oldState = oldState;
        this.newState = newState;
    }

    public TournamentLevelState getOldState() {
        return oldState;
    }

    public TournamentLevelState getNewState() {
        return newState;
    }

    public TournamentLevel getLevel() {
        return level;
    }
}