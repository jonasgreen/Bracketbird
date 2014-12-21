package com.bracketbird.client.service.rtc;


import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.model.keys.TournamentId;

import java.io.*;

/**
 *
 */
public class RTCRequest implements Serializable {
    private static final long serialVersionUID = -3526727409609971522L;

    private TournamentId tournamentId;
    private REvent event;
    private Long lastDoneEventId;
    private String clientId;

    public RTCRequest(REvent event, Long lastDoneEventId, TournamentId tournamentId, String clientId) {
        this.lastDoneEventId = lastDoneEventId;
        this.tournamentId = tournamentId;
        this.event = event;
        this.clientId = clientId;
    }


    private RTCRequest() {
    }


    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public String getClientId() {
        return clientId;
    }

    public REvent getEvent() {
        return event;
    }

    public Long getLastDoneEventId() {
        return lastDoneEventId;
    }
}
