package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.keys.TeamId;

import java.util.List;


/**
 *
 */
public class SeedingChangedEvent extends TournamentEvent {


    private List<TeamId> seedings;

    public SeedingChangedEvent(boolean fromClient, List<TeamId> seedings) {
        super(fromClient);
        this.seedings = seedings;
    }

    public List<TeamId> getSeedings() {
        return seedings;
    }
}