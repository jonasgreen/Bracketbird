package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;

/**
 *
 */
public class UpdateTeamSeedingEvent extends REvent<UpdateTeamSeedingEventHandler, TeamId>{

    private static final long serialVersionUID = -2655835684157046604L;
    private Integer seeding;

    public UpdateTeamSeedingEvent() {
    }

    public UpdateTeamSeedingEvent(Long id, TeamId modelId, Integer seed) {
        super(id, modelId);
        this.seeding = seed;
    }

    public Integer getSeeding() {
        return seeding;
    }

    @Override
    public Class<UpdateTeamSeedingEventHandler> getHandler() {
        return TYPE();
    }

    public static Class<UpdateTeamSeedingEventHandler> TYPE(){
        return UpdateTeamSeedingEventHandler.class;
    }

    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
