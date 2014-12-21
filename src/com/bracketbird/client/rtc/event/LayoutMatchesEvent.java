package com.bracketbird.client.rtc.event;

import com.bracketbird.client.model.keys.StageId;

/**
 *
 */
public class LayoutMatchesEvent extends REvent<LayoutMatchesEventHandler, StageId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private LayoutMatchesEvent() {
    }

    public LayoutMatchesEvent(Long eventId, StageId id) {
        super(eventId, id);
        setChangeState(true);
    }

    @Override
    public Class<LayoutMatchesEventHandler> getHandler() {
        return LayoutMatchesEventHandler.class;
    }
}
