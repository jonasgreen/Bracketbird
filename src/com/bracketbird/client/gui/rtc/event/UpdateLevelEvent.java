package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TournamentLevelId;
import com.bracketbird.client.model.tournament.LevelSettings;

/**
 *
 */
public class UpdateLevelEvent extends REvent<UpdateLevelEventHandler, TournamentLevelId>{

    private static final long serialVersionUID = -2655835684157046604L;
    private LevelSettings levelSettings;

    public UpdateLevelEvent() {
    }

    public UpdateLevelEvent(Long id, TournamentLevelId tlId, LevelSettings ls) {
        super(id, tlId);
        this.levelSettings = ls;
    }

    public LevelSettings getLevelSettings() {
        return levelSettings;
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
