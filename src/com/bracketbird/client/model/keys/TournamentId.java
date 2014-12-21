package com.bracketbird.client.model.keys;

/**
 *
 */
public class TournamentId extends EntityId {
    private static final long serialVersionUID = 6723107406859552487L;

    public TournamentId() {
    }

    public TournamentId(String encodedKey) {
        super(encodedKey, "tournamentKey");
    }
}