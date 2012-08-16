package com.bracketbird.clientcore.model.keys;

/**
 *
 */
public class ConstantId extends EntityId {
    private static final long serialVersionUID = 5401754393432223530L;

    public ConstantId() {
    }

    public ConstantId(String encodedKey) {
        super(encodedKey, "constantKey");
    }
}