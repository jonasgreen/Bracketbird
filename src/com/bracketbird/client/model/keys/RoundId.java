package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.EntityId;

/**
 *
 */
public class RoundId extends EntityId {
    private static final long serialVersionUID = 4167975269781064059L;

    public RoundId() {
    }

    public RoundId(String encodedKey) {
        super(encodedKey, "roundKey");
    }
}