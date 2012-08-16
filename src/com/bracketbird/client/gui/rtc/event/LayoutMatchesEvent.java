package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TournamentLevelId;

/**
 *
 */
public class LayoutMatchesEvent extends REvent<LayoutMatchesEventHandler, TournamentLevelId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private LayoutMatchesEvent() {
    }

    public LayoutMatchesEvent(Long eventId, TournamentLevelId id) {
        super(eventId, id);
        setChangeState(true);
    }

    @Override
    public Class<LayoutMatchesEventHandler> getHandler() {
        return LayoutMatchesEventHandler.class;
    }
}
