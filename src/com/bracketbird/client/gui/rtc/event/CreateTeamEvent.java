package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;

/**
 *
 */
public class CreateTeamEvent extends REvent<CreateTeamEventHandler, TeamId>{

    private static final long serialVersionUID = -2655835684157046604L;

    public CreateTeamEvent() {
    }

    public CreateTeamEvent(Long eventId, TeamId teamId) {
        super(eventId, teamId);
    }

    @Override
    public Class<CreateTeamEventHandler> getHandler() {
        return CreateTeamEventHandler.class;
    }
}
