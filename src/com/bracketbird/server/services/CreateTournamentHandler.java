package com.bracketbird.server.services;


import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.model.keys.UserId;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.server.repository.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;

import java.util.*;


/**
 *
 */
public class CreateTournamentHandler extends AbstractActionHandler implements ActionHandler<CreateTournamentAction, TournamentResult> {
    TournamentRepository repos = new TournamentRepository();
    TournamentChannelRepository tCRep = new TournamentChannelRepository();
    TournamentBrickRepository tbRep = new TournamentBrickRepository();

    public TournamentResult execute(CreateTournamentAction action) throws ApplicationException {
        List<REvent> evevntList = null;
        Tournament t;

        TournamentId tId = createTournamentId();
        createTournamentBrick(tId, action.getUserId(), action.getNameOfTournament());
        try {
            PMF.startTransaction();

            t = repos.create(new CreateTournament(action.getNameOfTournament(), tId));

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
        
        return new TournamentResult(t, evevntList);
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

    private TournamentBrick createTournamentBrick(TournamentId tournamentId, UserId userId, String tournamentName) throws ApplicationException {
        if (userId == null) {
            return null;
        }
        try {
            PMF.startTransaction();
            CreateTournamentBrick cBrick = new CreateTournamentBrick();
            cBrick.setTournamentId(tournamentId);
            cBrick.setUserId(userId);
            cBrick.setTournamentName(tournamentName);
            TournamentBrick brick = tbRep.create(cBrick);
            PMF.commitTransaction();
            return brick;

        }
        finally {
            PMF.endTransaction();
        }
    }

}