package com.bracketbird.client.service;

import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.tournament.CreateTournament;
import com.bracketbird.clientcore.model.keys.UserId;
import com.bracketbird.clientcore.service.AbstractAction;
import com.bracketbird.clientcore.service.Action;
import com.bracketbird.clientcore.service.ListResult;

/**
 *
 */
public class GetSingleAppInfoAction extends AbstractAction  implements Action<ListResult>{
    private static final long serialVersionUID = -6889708966356974734L;

    private TournamentId tournamentId;
    private UserId userId;
    private CreateTournament createTournament;

    public GetSingleAppInfoAction() {
    }




    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
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