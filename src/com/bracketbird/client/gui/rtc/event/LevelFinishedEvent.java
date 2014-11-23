package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.keys.StageId;

import java.util.List;

/**
 *
 */
public class LevelFinishedEvent extends REvent<LevelFinishedEventHandler, StageId>{

    private static final long serialVersionUID = -5592485995369867791L;

    private List<TeamId[]> finalRank;

    private LevelFinishedEvent() {
    }

    public LevelFinishedEvent(Long eventId, StageId id, List<TeamId[]> finalRank) {
        super(eventId, id);
        setChangeState(true);
        this.finalRank = finalRank;
    }

    @Override
    public Class<LevelFinishedEventHandler> getHandler() {
        return LevelFinishedEventHandler.class;
    }

    public List<TeamId[]> getFinalRank() {
        return finalRank;
    }
}
