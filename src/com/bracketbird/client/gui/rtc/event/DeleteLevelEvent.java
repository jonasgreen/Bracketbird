package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TournamentLevelId;

/**
 *
 */
public class DeleteLevelEvent extends REvent<DeleteLevelEventHandler, TournamentLevelId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private DeleteLevelEvent() {
    }

    public DeleteLevelEvent(Long eventId, TournamentLevelId id) {
        super(eventId, id);
    }

    @Override
    public Class<DeleteLevelEventHandler> getHandler() {
        return DeleteLevelEventHandler.class;
    }
}
