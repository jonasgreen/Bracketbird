package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class GetSingleAppInfoHandler extends AbstractActionHandler implements ActionHandler<GetSingleAppInfoAction, SingleResult<SingleAppInfo>> {


    public SingleResult<SingleAppInfo> execute(GetSingleAppInfoAction action) throws ApplicationException {
        SingleAppInfo sai = new SingleAppInfo();

     
        TournamentRepository tournamentRep = new TournamentRepository();
        if (action.getCreateTournament() != null) {
            CreateTournamentHandler crHandler = new CreateTournamentHandler();
            CreateTournamentAction a = new CreateTournamentAction();
           // a.setCreateTournament(action.getCreateTournament());
            a.setUserId(action.getUserId());

            TournamentResult crResult = crHandler.execute(a);
            sai.setTournament(crResult.getTournament());
            sai.setTournamentEventLog(crResult.getEventLogs());

        }
        else {
            sai.setTournament(tournamentRep.get(action.getTournamentId()));
            sai.setTournamentEventLog(tournamentRep.getTournamentLog(action.getTournamentId()));
        }



        return new SingleResult<SingleAppInfo>(sai);


    }

    public Class<GetSingleAppInfoAction> getActionType() {
        return GetSingleAppInfoAction.class;
    }


}