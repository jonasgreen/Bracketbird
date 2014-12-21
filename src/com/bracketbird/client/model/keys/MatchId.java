package com.bracketbird.client.model.keys;

/**
 *
 */
public class MatchId extends EntityId {
    private static final long serialVersionUID = 4167975269781064059L;

    public MatchId() {
    }

    public MatchId(String encodedKey) {
        super(encodedKey, "matchKey");
    }
}