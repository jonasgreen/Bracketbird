package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.keys.TournamentLevelId;
import com.bracketbird.client.model.tournament.LevelSettings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UpdateSeedingEvent extends REvent<UpdateSeedingEventHandler, TournamentId>{

    private List<TeamId> seedings = new ArrayList<TeamId>();

    public UpdateSeedingEvent() {
    }

    public UpdateSeedingEvent(Long id, TournamentId tId, List<TeamId> ids) {
        super(id, tId);
        this.seedings = ids;
    }


    @Override
    public Class<UpdateSeedingEventHandler> getHandler() {
        return UpdateSeedingEventHandler.class;
    }

    @Override
    public boolean isUpdateEvent(){
        return true;
    }

    public List<TeamId> getSeedings() {
        return seedings;
    }

    public void setSeedings(List<TeamId> seedings) {
        this.seedings = seedings;
    }
}
