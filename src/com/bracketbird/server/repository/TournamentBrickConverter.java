package com.bracketbird.server.repository;

import com.bracketbird.client.model.CreateTournamentBrick;
import com.bracketbird.client.model.TournamentBrick;
import com.bracketbird.clientcore.util.StringUtil;
import com.bracketbird.server.jdo.club.TournamentBrickJDO;
import com.google.appengine.api.datastore.Key;


/**
 *
 */
public class TournamentBrickConverter extends Converter<TournamentBrickJDO, TournamentBrick, CreateTournamentBrick> {

    public TournamentBrickJDO convert(CreateTournamentBrick model) {
        if (model == null) {
            return null;
        }
        TournamentBrickJDO jdo = new TournamentBrickJDO();
        jdo.setKey(KeyFac.createTournamentBrickKey(model.getUserId()));
        jdo.setTournamentKey(KeyFac.convert(model.getTournamentId()));
        jdo.setTournamentName(model.getTournamentName());
        return jdo;
    }

    public TournamentBrick convert(TournamentBrickJDO jdo) {
        if (jdo == null) {
            return null;
        }
        TournamentBrick tb = new TournamentBrick();
        tb.setId(KeyFac.getTournamentBrickId(jdo.getKey()));
        tb.setTournamentId(KeyFac.getTournamentId(jdo.getTournamentKey()));
        tb.setLastActivityDate(jdo.getLastChangeDate());
        tb.setTournamentName(tb.getTournamentName());
        return tb;

    }


    @Override
    public void updateJDO(TournamentBrickJDO jdo, TournamentBrick model) {

    }


}