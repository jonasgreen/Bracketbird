package com.bracketbird.clientcore.model.keys;


/**
 *
 */
public class UserId extends EntityId {
    private static final long serialVersionUID = 5401754393432223530L;

    public UserId() {
    }

    public UserId(String encodedKey) {
        super(encodedKey, "userKey");
    }

}