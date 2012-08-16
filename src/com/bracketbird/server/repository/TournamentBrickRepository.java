package com.bracketbird.server.repository;


import com.bracketbird.client.model.CreateTournamentBrick;
import com.bracketbird.client.model.TournamentBrick;
import com.bracketbird.clientcore.appcontrol.ApplicationException;
import com.bracketbird.clientcore.model.keys.UserId;
import com.bracketbird.server.dao.Dao;
import com.bracketbird.server.dao.PMF;
import com.bracketbird.server.jdo.club.TournamentBrickJDO;
import com.google.appengine.api.datastore.Key;

/**
 *
 */
public class TournamentBrickRepository extends Repository<TournamentBrickJDO, TournamentBrick, CreateTournamentBrick> {

    public TournamentBrickRepository() {
        this(new Dao(TournamentBrickJDO.class), new TournamentBrickConverter());
    }

    public TournamentBrickRepository(Dao dao, Converter con) {
        super(dao, con);
    }

    public Class<TournamentBrick> getRepositoryType() {
        return TournamentBrick.class;
    }



}