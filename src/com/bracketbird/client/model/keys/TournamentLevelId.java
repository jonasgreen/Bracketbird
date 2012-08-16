package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class TournamentLevelId extends EntityId {
    private static final long serialVersionUID = 4167975269781064059L;

    public TournamentLevelId() {
    }

    public TournamentLevelId(String encodedKey) {
        super(encodedKey, "tournamentLevelKey");
    }
}