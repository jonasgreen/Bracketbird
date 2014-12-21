package com.bracketbird.client.model.keys;

/**
 *
 */
public class TeamId extends EntityId {
    private static final long serialVersionUID = -4733753081507960880L;

    public TeamId() {
        super();
    }

    public TeamId(String encodedKey) {
        super(encodedKey, "TeamKey");
    }
}