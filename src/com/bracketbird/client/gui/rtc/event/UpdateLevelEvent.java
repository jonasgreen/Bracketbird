package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TournamentLevelId;
import com.bracketbird.client.model.tournament.StageSettings;

/**
 *
 */
public class UpdateLevelEvent extends REvent<UpdateLevelEventHandler, TournamentLevelId>{

    private static final long serialVersionUID = -2655835684157046604L;
    private StageSettings stageSettings;

    public UpdateLevelEvent() {
    }

    public UpdateLevelEvent(Long id, TournamentLevelId tlId, StageSettings ls) {
        super(id, tlId);
        this.stageSettings = ls;
    }

    public StageSettings getStageSettings() {
        return stageSettings;
    }

    @Override
    public Class<UpdateLevelEventHandler> getHandler() {
        return UpdateLevelEventHandler.class;
    }

    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
