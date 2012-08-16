package com.bracketbird.client.model.tournament;


/**
 *
 */
public class TournamentLevelDeletedEvent extends TournamentEvent {

    private TournamentLevel level;

    public TournamentLevelDeletedEvent(TournamentLevel level, boolean fromClient) {
        super(fromClient);
        this.level = level;
    }

    public TournamentLevel getLevel() {
        return level;
    }

    public void setLevel(TournamentLevel level) {
        this.level = level;
    }
}