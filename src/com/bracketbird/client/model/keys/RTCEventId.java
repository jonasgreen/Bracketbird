package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class RTCEventId extends EntityId {
    private static final long serialVersionUID = 2637151670622880157L;

    public RTCEventId() {
    }

    public RTCEventId(String encodedKey) {
        super(encodedKey, "rtceventKey");
    }
}