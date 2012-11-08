package com.bracketbird.client.model.tournament;


/**
 *
 */
public class TournamentLevelEvent extends TournamentEvent {

    public enum LevelAction{
        create,
        update,
        delete,
    }
    private TournamentLevel level;
    private LevelAction action;

    public TournamentLevelEvent(TournamentLevel level, LevelAction a, boolean fromClient) {
        super(fromClient);
        this.level = level;
        this.action = a;
    }

    public TournamentLevel getLevel() {
        return level;
    }

    public void setLevel(TournamentLevel level) {
        this.level = level;
    }

    public LevelAction getAction() {
        return action;
    }

    public void setAction(LevelAction action) {
        this.action = action;
    }
}