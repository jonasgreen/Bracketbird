package com.bracketbird.client.rtc.event;

import com.bracketbird.client.model.keys.StageId;

/**
 *
 */
public class DeleteLevelEvent extends REvent<DeleteLevelEventHandler, StageId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private DeleteLevelEvent() {
    }

    public DeleteLevelEvent(Long eventId, StageId id) {
        super(eventId, id);
    }

    @Override
    public Class<DeleteLevelEventHandler> getHandler() {
        return DeleteLevelEventHandler.class;
    }
}
