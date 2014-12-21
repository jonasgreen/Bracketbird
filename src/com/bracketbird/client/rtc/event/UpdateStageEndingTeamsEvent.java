package com.bracketbird.client.rtc.event;

import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.keys.StageId;

import java.util.List;

/**
 *
 */
public class UpdateStageEndingTeamsEvent extends REvent<UpdateStageEndingTeamsEventHandler, StageId>{

    private static final long serialVersionUID = -5592485995369867791L;

    private List<TeamId[]> finalRank;

    private UpdateStageEndingTeamsEvent() {
    }

    public UpdateStageEndingTeamsEvent(Long eventId, StageId id, List<TeamId[]> finalRank) {
        super(eventId, id);
        setChangeState(true);
        this.finalRank = finalRank;
    }

    @Override
    public Class<UpdateStageEndingTeamsEventHandler> getHandler() {
        return UpdateStageEndingTeamsEventHandler.class;
    }

    public List<TeamId[]> getFinalRank() {
        return finalRank;
    }
}
