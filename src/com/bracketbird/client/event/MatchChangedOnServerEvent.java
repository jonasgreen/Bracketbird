package com.bracketbird.client.event;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.shared.*;

/**
 *
 */
public class MatchChangedOnServerEvent extends GwtEvent {

    private TournamentLevelId tournamentLevelId;
    private Match match;

    public static Type TYPE = new Type();


    public MatchChangedOnServerEvent(TournamentLevelId id, Match m) {
        this.tournamentLevelId = id;
        this.match = m;
    }

    public TournamentLevelId getSubtournamentId() {
        return tournamentLevelId;
    }

    public Match getMatch() {
        return match;
    }

    public Type getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(EventHandler handler) {
    	((MatchCreatedOnServerHandler)handler).onChange(this);
    }
}