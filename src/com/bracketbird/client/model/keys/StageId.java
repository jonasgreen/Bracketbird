package com.bracketbird.client.model.keys;

/**
 *
 */
public class StageId extends EntityId {
    private static final long serialVersionUID = 4167975269781064059L;

    public StageId() {
    }

    public StageId(String encodedKey) {
        super(encodedKey, "tournamentLevelKey");
    }
}