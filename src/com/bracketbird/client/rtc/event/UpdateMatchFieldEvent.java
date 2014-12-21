package com.bracketbird.client.rtc.event;

import com.bracketbird.client.model.keys.MatchId;

/**
 *
 */
public class UpdateMatchFieldEvent extends REvent<UpdateMatchFieldEventHandler, MatchId>{

    private static final long serialVersionUID = 5466028218065429397L;
    private String field;

    public UpdateMatchFieldEvent() {
    }

    public UpdateMatchFieldEvent(Long id, MatchId modelId, String field) {
        super(id, modelId);
        this.field =field;
    }

    public String getField() {
        return field;
    }

    @Override
    public Class<UpdateMatchFieldEventHandler> getHandler() {
        return UpdateMatchFieldEventHandler.class;
    }


    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
