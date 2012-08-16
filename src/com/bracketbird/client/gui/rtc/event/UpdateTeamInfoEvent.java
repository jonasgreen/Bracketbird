package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;

/**
 *
 */
public class UpdateTeamInfoEvent extends REvent<UpdateTeamInfoEventHandler, TeamId>{

    private static final long serialVersionUID = -2655835684157046604L;
    private String info;

    public UpdateTeamInfoEvent() {
    }

    public UpdateTeamInfoEvent(Long id, TeamId modelId, String info) {
        super(id, modelId);
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public Class<UpdateTeamInfoEventHandler> getHandler() {
        return UpdateTeamInfoEventHandler.class;
    }


    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
