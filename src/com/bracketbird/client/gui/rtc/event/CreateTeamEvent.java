package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;

/**
 *
 */
public class CreateTeamEvent extends REvent<CreateTeamEventHandler, TeamId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private String teamName;
    private int seeding;

    public CreateTeamEvent() {
    }

    public CreateTeamEvent(String teamName, int seeding, TeamId teamId) {
        super(null, teamId);
        this.teamName = teamName;
        this.seeding = seeding;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getSeeding() {
        return seeding;
    }

    @Override
    public Class<CreateTeamEventHandler> getHandler() {
        return CreateTeamEventHandler.class;
    }
}
