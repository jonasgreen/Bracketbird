package com.bracketbird.client.service;

import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.model.keys.TournamentId;

import java.util.List;


/**
 *
 */
public class TournamentResult implements Result {
    private static final long serialVersionUID = -2615108262997306218L;


    private TournamentId tournamentId;
    private TournamentChannelId channelId;

    private String tournamentUrl;
    private String tournamentViewUrl;
    private boolean viewOnly;

    private List<REvent> eventLogs;

    public TournamentResult(List<REvent> eventLogs) {
        this.eventLogs = eventLogs;
    }

    public TournamentResult() {
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }

    public TournamentChannelId getChannelId() {
        return channelId;
    }

    public void setChannelId(TournamentChannelId channelId) {
        this.channelId = channelId;
    }

    public String getTournamentUrl() {
        return tournamentUrl;
    }

    public void setTournamentUrl(String tournamentUrl) {
        this.tournamentUrl = tournamentUrl;
    }

    public String getTournamentViewUrl() {
        return tournamentViewUrl;
    }

    public void setTournamentViewUrl(String tournamentViewUrl) {
        this.tournamentViewUrl = tournamentViewUrl;
    }

    public void setEventLogs(List<REvent> eventLogs) {
        this.eventLogs = eventLogs;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public List<REvent> getEventLogs() {
        return eventLogs;
    }
}