package com.bracketbird.client.service;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class GetTournamentChangesAction extends AbstractAction implements Action<ListResult>{
    private static final long serialVersionUID = -1095211233453977436L;

    private TournamentId clubId;
    private Integer stampId;

    public GetTournamentChangesAction() {
    }

    public GetTournamentChangesAction(TournamentId id, Integer stampId) {
        this.clubId =id;
        this.stampId = stampId;
    }

    public TournamentId getClubId() {
        return clubId;
    }

    public void setClubId(TournamentId clubId) {
        this.clubId = clubId;
    }

    public Integer getStampId() {
        return stampId;
    }

    public void setStampId(Integer stampId) {
        this.stampId = stampId;
    }
}