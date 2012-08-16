package com.bracketbird.client.service;

import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.clientcore.service.Result;

import java.util.*;


/**
 *
 */
public class TournamentResult implements Result {
    private static final long serialVersionUID = -2615108262997306218L;

    private Tournament tournament;
    private List<REvent> eventLogs;

    public TournamentResult(Tournament tournament, List<REvent> eventLogs) {
        this.tournament = tournament;
        this.eventLogs = eventLogs;
    }

    public TournamentResult() {
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<REvent> getEventLogs() {
        return eventLogs;
    }
}