package com.bracketbird.client.service.rtc;


import com.bracketbird.client.gui.rtc.event.REvent;

import java.io.*;
import java.util.*;

/**
 *
 */
public class RTCResponse implements Serializable {
    public static enum State{
        succeed,
        state_changed_wait_for_update,
        merge,
    }

    private static final long serialVersionUID = 2736070612470978092L;

    private long eventId;
    private long stateId;
    private State state;

    public RTCResponse() {
    }

    public RTCResponse(State state) {
        this.state = state;
    }

    public RTCResponse(long stateId, long eventId, State state) {
        this.stateId = stateId;
        this.eventId = eventId;
        this.state = state;
    }

    public long getEventId() {
        return eventId;
    }

    public long getStateId() {
        return stateId;
    }

    public State getState() {
        return state;
    }
}
