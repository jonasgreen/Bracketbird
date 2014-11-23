package com.bracketbird.server.services;


import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.service.GetTournamentAction;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.ApplicationException;
import com.bracketbird.clientcore.service.SingleFinder;
import com.bracketbird.clientcore.service.StringSingleFinder;
import com.bracketbird.server.repository.TournamentRepository;

import java.util.Collection;
import java.util.List;


/**
 *
 */
public class GetTournamentHandler extends AbstractActionHandler  implements ActionHandler<GetTournamentAction, TournamentResult> {


    public TournamentResult execute(GetTournamentAction action) throws ApplicationException {

        StringSingleFinder finder;
        TournamentRepository rep = new TournamentRepository();
        boolean viewOnly = action.getTournamentUrl() != null && action.getTournamentUrl().endsWith("v");
        if(viewOnly){
            finder = new StringSingleFinder("viewUrl", SingleFinder.Operator.EQUAL_TO, action.getTournamentUrl());
        }
        else{
            finder = new StringSingleFinder("url", SingleFinder.Operator.EQUAL_TO, action.getTournamentUrl());
        }

        Collection<Tournament> tournaments = rep.findBy(finder);
        if(tournaments.isEmpty()){
            return new TournamentResult();
        }

        Tournament t = tournaments.iterator().next();
        t.setViewOnly(viewOnly);
        List<REvent> events = rep.getTournamentLog(t.getId());

        TournamentResult result = new TournamentResult(events);
        result.setTournamentId(t.getId());
        result.setChannelId(t.getTournamentChannelId());
        result.setTournamentViewUrl(t.getViewUrl());
        result.setTournamentUrl(t.getUrl());
        result.setViewOnly(t.isViewOnly());

        return result;
    }

    public Class<GetTournamentAction> getActionType() {
        return GetTournamentAction.class;
    }


}