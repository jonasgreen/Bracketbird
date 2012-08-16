package com.bracketbird.client.model.tournament;


/**
 *
 */
public class TournamentNameChangedEvent extends TournamentEvent {

    private String name;

    public TournamentNameChangedEvent(String name, boolean fromClient) {
        super(fromClient);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}