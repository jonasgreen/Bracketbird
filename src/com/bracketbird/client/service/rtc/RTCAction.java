package com.bracketbird.client.service.rtc;

import com.bracketbird.client.service.AbstractAction;
import com.bracketbird.client.service.Action;

/**
 *
 */
public class RTCAction extends AbstractAction implements Action<RTCResult> {
    private static final long serialVersionUID = 4959769561124027412L;

    private RTCRequest request;

    public RTCAction() {
    }

    public RTCAction(RTCRequest request) {

        this.request = request;
    }

    public RTCRequest getRequest() {
        return request;
    }

    public void setRequest(RTCRequest request) {
        this.request = request;
    }
}