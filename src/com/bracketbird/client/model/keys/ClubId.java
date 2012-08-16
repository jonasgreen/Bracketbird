package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class ClubId extends EntityId {
    private static final long serialVersionUID = 5401754393432223530L;

    public ClubId() {
    }

    public ClubId(String encodedKey) {
        super(encodedKey, "clubKey");
    }
}
