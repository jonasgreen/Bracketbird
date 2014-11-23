package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.StageType;
import com.bracketbird.client.model.keys.StageId;

/**
 *
 */
public class CreateLevelEvent extends REvent<CreateLevelEventHandler, StageId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private StageType stageType;

    public CreateLevelEvent() {
    }

    public CreateLevelEvent(Long eventId, StageType type, StageId id) {
        super(eventId, id);
        this.stageType = type;
    }

    public StageType getStageType() {
        return stageType;
    }

    @Override
    public Class<CreateLevelEventHandler> getHandler() {
        return CreateLevelEventHandler.class;
    }


}
