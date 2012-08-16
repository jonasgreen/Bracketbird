package com.bracketbird.client.service.rtc;


import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class RTCResult implements Result {
    private static final long serialVersionUID = -5092014046936048603L;

    private RTCResponse response;


    public RTCResult(RTCResponse response) {
        this.response = response;
    }

    public RTCResult() {
    }

    public RTCResponse getResponse() {
        return response;
    }

    public void setResponse(RTCResponse response) {
        this.response = response;
    }
}