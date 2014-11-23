package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.StageId;
import com.bracketbird.client.model.tournament.StageSettings;

/**
 *
 */
public class UpdateStageEvent extends REvent<UpdateStageEventHandler, StageId>{

    private static final long serialVersionUID = -2655835684157046604L;
    private StageSettings stageSettings;

    public UpdateStageEvent() {
    }

    public UpdateStageEvent(Long id, StageId tlId, StageSettings ls) {
        super(id, tlId);
        this.stageSettings = ls;
    }

    public StageSettings getStageSettings() {
        return stageSettings;
    }

    @Override
    public Class<UpdateStageEventHandler> getHandler() {
        return UpdateStageEventHandler.class;
    }

    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
