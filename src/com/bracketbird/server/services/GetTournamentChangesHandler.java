package com.bracketbird.server.services;


import com.bracketbird.client.service.*;
import com.bracketbird.server.repository.TournamentRepository;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;


/**
 *
 */
public class GetTournamentChangesHandler extends AbstractActionHandler  implements ActionHandler<GetTournamentChangesAction, ListResult> {


    public ListResult execute(GetTournamentChangesAction action) throws ApplicationException {

        TournamentRepository repos = new TournamentRepository();

        return new ListResult(null);
    }

    public Class<GetTournamentChangesAction> getActionType() {
        return GetTournamentChangesAction.class;
    }


}