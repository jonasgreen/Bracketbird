package com.bracketbird.client.model.tournament;

import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.rtc.event.InitEvent;
import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.rtc.event.REventHandler;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.service.TournamentResult;

import java.util.ArrayList;
import java.util.List;

public class RTCTest extends RTC {

    private long eventId = 1;
    private long uuid = 0;

    private static RTCTest instance;

    private RTCTest() {
    }


    public static RTCTest getInstance() {
        if (instance == null) {
            instance = new RTCTest();
            instance.initTournament();
            RTC.instance = instance;
        }
        return instance;
    }

    private void initTournament() {
        TournamentId id = new TournamentId("tournamentId_test");

        REvent initEvent = new InitEvent(nextEventId(), id);
        initEvent.setStateId(0L);

        List<REvent> events = new ArrayList<REvent>();
        events.add(initEvent);


        TournamentResult result = new TournamentResult(events);
        result.setTournamentId(id);
        result.setTournamentUrl("urlTest");
        result.setTournamentViewUrl("viewUrlTest");
        result.setViewOnly(false);

        loadTournament(result, events, true);
    }


    public void executeEvent(REvent<?, ?> event) {
        REventHandler<REvent<?, ?>> handler = handlers.get(event.getHandler());
        if (handler == null) {
            throw new RuntimeException("No handler registered for event: " + event.getClass().getName());
        }
        //makes it look like all events are from server - ie no server call will happen during test.
        event.setEventId(nextEventId());
        handler.handleEvent(event);
    }

    public long nextEventId(){
        return eventId++;
    }

    protected void initGuiListeners(Tournament t) {

    }

    public void loadTournament(TournamentResult r, List<REvent> events, boolean justCreated) {
        this.tournament = new Tournament();
        tournament.setViewOnly(r.isViewOnly());
        tournament.setTournamentChannelId(r.getChannelId());
        tournament.setUrl(r.getTournamentUrl());
        tournament.setViewUrl(r.getTournamentViewUrl());
        tournament.setId(r.getTournamentId());
    }

    @Override
    protected String nextUUID(){
        return ""+uuid++;
    }





}
