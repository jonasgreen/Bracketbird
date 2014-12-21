package com.bracketbird.client.service.rtc;

import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.service.AbstractAction;
import com.bracketbird.client.service.Action;

/**
 *
 */
public class GetREventAction extends AbstractAction implements Action<GetREventResult> {
    private static final long serialVersionUID = 4959769561124027412L;

    private TournamentId tournamentId;
    private Long eventId;



    public GetREventAction() {
    }

    public GetREventAction(TournamentId tournamentId, Long eventId) {
        this.tournamentId = tournamentId;
        this.eventId = eventId;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public Long getEventId() {
        return eventId;
    }
}