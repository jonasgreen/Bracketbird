package com.bracketbird.client.service;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.CreateTournament;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class GetSingleAppInfoAction extends AbstractAction  implements Action<ListResult>{
    private static final long serialVersionUID = -6889708966356974734L;

    private TournamentId tournamentId;
    private ClubId clubId;
    private UserId userId;
    private CreateTournament createTournament;

    public GetSingleAppInfoAction() {
    }


    public GetSingleAppInfoAction(UserId userId, TournamentId tournamentId, ClubId clubId) {
        this.userId = userId;
        this.tournamentId = tournamentId;
        this.clubId = clubId;
    }


    public GetSingleAppInfoAction(UserId userId, ClubId clubId) {
        this.userId = userId;
        this.clubId = clubId;
    }


    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }

    public ClubId getClubId() {
        return clubId;
    }

    public void setClubId(ClubId clubId) {
        this.clubId = clubId;
    }


    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public CreateTournament getCreateTournament() {
        return createTournament;
    }

    public void setCreateTournament(CreateTournament createTournament) {
        this.createTournament = createTournament;
    }
}