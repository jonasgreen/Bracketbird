package com.bracketbird.client.service.rtc;

import com.bracketbird.clientcore.service.*;


/**
 *
 */
public class CreateChannelTokenResult implements Result {

    private static final long serialVersionUID = -2892713366484783889L;
    private String token;
    private String clientId;

    public CreateChannelTokenResult(String token, String clientId) {
        this.token = token;
        this.clientId = clientId;
    }

    public CreateChannelTokenResult() {
    }

    public String getToken() {
        return token;
    }

    public String getClientId() {
        return clientId;
    }
}