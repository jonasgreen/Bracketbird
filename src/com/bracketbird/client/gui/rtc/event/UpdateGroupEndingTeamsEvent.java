package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.GroupId;
import com.bracketbird.client.model.keys.TeamId;

import java.util.List;

/**
 *
 */
public class UpdateGroupEndingTeamsEvent extends REvent<UpdateGroupEndingTeamsEventHandler, GroupId>{

    private static final long serialVersionUID = -5592485995369867791L;

    private List<TeamId> finalRank;

    private UpdateGroupEndingTeamsEvent() {
    }

    public UpdateGroupEndingTeamsEvent(Long eventId, GroupId id, List<TeamId> finalRank) {
        super(eventId, id);
        setChangeState(true);
        this.finalRank = finalRank;
    }

    @Override
    public Class<UpdateGroupEndingTeamsEventHandler> getHandler() {
        return UpdateGroupEndingTeamsEventHandler.class;
    }

    public List<TeamId> getFinalRank() {
        return finalRank;
    }
}
