package com.bracketbird.client.service;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class TournamentIndexAction extends AbstractAction implements Action<ListResult>{
    private static final long serialVersionUID = 413483738729224787L;

    private ClubId clubId;

    public TournamentIndexAction() {
    }

    public TournamentIndexAction(ClubId id) {
        this.clubId =id;
    }

    public ClubId getClubId() {
        return clubId;
    }

    public void setClubId(ClubId clubId) {
        this.clubId = clubId;
    }
}