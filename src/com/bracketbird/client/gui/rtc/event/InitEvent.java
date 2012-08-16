package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TournamentId;

/**
 *
 */
public class InitEvent extends REvent<InitEventHandler, TournamentId>{


    private static final long serialVersionUID = 6030697952599306509L;

    public InitEvent() {
    }

    public InitEvent(Long eventId, TournamentId id) {
        super(eventId, id);
    }

    @Override
    public Class<InitEventHandler> getHandler() {
        return InitEventHandler.class;
    }

    @Override
    public String getEventName(){
        return "CreateTournamentEvent";
    }
}
