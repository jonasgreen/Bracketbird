package com.bracketbird.client.model.keys;

/**
 *
 */
public class TournamentChannelId extends EntityId {
    private static final long serialVersionUID = 6723107406859552487L;

    public TournamentChannelId() {
    }

    public TournamentChannelId(String encodedKey) {
        super(encodedKey, "tournamentChannelKey");
    }
}