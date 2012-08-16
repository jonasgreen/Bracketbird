package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.EntityId;

/**
 *
 */
public class TournamentBrickId extends EntityId {
    private static final long serialVersionUID = 2046667486232935465L;

    public TournamentBrickId() {
    }

    public TournamentBrickId(String encodedKey) {
        super(encodedKey, "tournamentBrickKey");
    }
}