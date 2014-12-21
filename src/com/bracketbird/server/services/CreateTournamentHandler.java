package com.bracketbird.server.services;


import com.bracketbird.client.appcontrol.ApplicationException;
import com.bracketbird.client.model.TournamentChannel;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.service.CreateTournamentAction;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.server.dao.PMF;
import com.bracketbird.server.repository.*;

import java.util.List;


/**
 *
 */
public class CreateTournamentHandler extends AbstractActionHandler implements ActionHandler<CreateTournamentAction, TournamentResult> {
    TournamentRepository repos = new TournamentRepository();
    TournamentChannelRepository tCRep = new TournamentChannelRepository();

    public TournamentResult execute(CreateTournamentAction action) throws ApplicationException {
        List<REvent> evevntList = null;
        Tournament t;

        TournamentId tId = createTournamentId();
        try {
            PMF.startTransaction();

            t = repos.create(new CreateTournament("Tournament", tId));

            CreateTournamentChannel channelCreator = new CreateTournamentChannel();
            channelCreator.setTournamentId(t.getId());
            TournamentChannel channel = tCRep.create(channelCreator);

            t.setTournamentChannelId(channel.getId());
            repos.update(t);

            PMF.commitTransaction();
            evevntList = repos.getTournamentLog(t.getId());
            
        }
        finally {
            PMF.endTransaction();
        }

        TournamentResult result = new TournamentResult(evevntList);
        result.setChannelId(t.getTournamentChannelId());
        result.setTournamentId(t.getId());
        result.setTournamentUrl(t.getUrl());
        result.setTournamentViewUrl(t.getViewUrl());
        result.setViewOnly(false);
        return result;
    }

    public Class<CreateTournamentAction> getActionType() {
        return CreateTournamentAction.class;
    }

    private TournamentId createTournamentId() {
        try {
            PMF.startTransaction();
            TournamentId id = KeyFac.getTournamentId(KeyFac.createTournamentKey());
            PMF.commitTransaction();
            return id;
        }
        finally {
            PMF.endTransaction();
        }
    }

}