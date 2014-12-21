package com.bracketbird.client.rtc.event;

import com.bracketbird.client.model.keys.TeamId;

/**
 *
 */
public class UpdateTeamNameEvent extends REvent<UpdateTeamNameEventHandler, TeamId>{

    private static final long serialVersionUID = -2655835684157046604L;
    private String name;

    public UpdateTeamNameEvent() {
    }

    public UpdateTeamNameEvent(Long id, TeamId modelId, String name) {
        super(id, modelId);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Class<UpdateTeamNameEventHandler> getHandler() {
        return UpdateTeamNameEventHandler.class;
    }


    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
