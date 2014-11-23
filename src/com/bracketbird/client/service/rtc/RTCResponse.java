package com.bracketbird.client.service.rtc;


import java.io.Serializable;

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

    private Long eventId;
    private Long stateId;
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

    public Long getEventId() {
        return eventId;
    }

    public Long getStateId() {
        return stateId;
    }

    public State getState() {
        return state;
    }
}
