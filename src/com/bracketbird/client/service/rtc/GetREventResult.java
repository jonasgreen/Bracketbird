package com.bracketbird.client.service.rtc;

import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.clientcore.service.Result;


/**
 *
 */
public class GetREventResult implements Result {

    private static final long serialVersionUID = -2671589254056443207L;
    private REvent event;

    public GetREventResult(REvent event) {
        this.event = event;
    }

    public GetREventResult() {
    }

    public REvent getEvent() {
        return event;
    }
}