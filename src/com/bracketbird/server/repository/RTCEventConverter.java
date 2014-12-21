package com.bracketbird.server.repository;

import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.server.jdo.RTCEventJDO;
import com.google.appengine.api.datastore.*;

import java.util.*;


/**
 *
 */
public class RTCEventConverter {

    public RTCEventJDO create(TournamentId tournamentId, REvent event) {
        RTCEventJDO jdo = new RTCEventJDO();
        jdo.setCreatedDate(new Date());
        jdo.setEvent(event);
        jdo.setStateId(event.getStateId());

        if (event.isChangeState()) {
            Key stateCounter = KeyFac.createRTCEventStateKey(tournamentId);
            event.setStateId(stateCounter.getId());
            jdo.setStateId(stateCounter.getId());
        }

        Key eventIdCounter = KeyFac.createRTCEventKey(tournamentId);
        event.setEventId(eventIdCounter.getId());
        jdo.setId(eventIdCounter.getId());

        jdo.setKey(eventIdCounter);
        jdo.setTournamentKey(KeyFac.convert(tournamentId));
        return jdo;
    }

    public List<REvent> create(Collection<RTCEventJDO> jdos) {
        List<REvent> list = new ArrayList<REvent>();
        for (RTCEventJDO jdo : jdos) {
            list.add(create(jdo));
        }
        return list;
    }

    public REvent create(RTCEventJDO jdo) {
        return jdo.getEvent();
    }


}