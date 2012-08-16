package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;

/**
 *
 */
public class DeleteTeamEvent extends REvent<DeleteTeamEventHandler, TeamId>{

    private static final long serialVersionUID = -2655835684157046604L;

    public DeleteTeamEvent() {
    }

    public DeleteTeamEvent(Long eventId, TeamId teamId) {
        super(eventId, teamId);
    }

    @Override
    public Class<DeleteTeamEventHandler> getHandler() {
        return DeleteTeamEventHandler.class;
    }
}
