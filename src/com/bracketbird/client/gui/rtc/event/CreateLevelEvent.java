package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TournamentLevelId;

/**
 *
 */
public class CreateLevelEvent extends REvent<CreateLevelEventHandler, TournamentLevelId>{

    private static final long serialVersionUID = -2655835684157046604L;

    private Integer levelType;

    public CreateLevelEvent() {
    }

    public CreateLevelEvent(Long eventId, Integer leveltype, TournamentLevelId id) {
        super(eventId, id);
        this.levelType = leveltype;
    }

    public Integer getLevelType() {
        return levelType;
    }

    @Override
    public Class<CreateLevelEventHandler> getHandler() {
        return CreateLevelEventHandler.class;
    }


}
