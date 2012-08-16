package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class MemberId extends EntityId {
    private static final long serialVersionUID = 2046667486232935465L;

    public MemberId() {
    }

    public MemberId(String encodedKey) {
        super(encodedKey, "memberKey");
    }
}