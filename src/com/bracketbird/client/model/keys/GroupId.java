package com.bracketbird.client.model.keys;

/**
 *
 */
public class GroupId extends EntityId {
    private static final long serialVersionUID = 4167975269781064059L;

    public GroupId() {
    }

    public GroupId(String encodedKey) {
        super(encodedKey, "groupKey");
    }
}