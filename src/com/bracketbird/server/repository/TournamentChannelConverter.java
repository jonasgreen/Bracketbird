package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.server.jdo.TournamentChannelJDO;


/**
 *
 */
public class TournamentChannelConverter extends Converter<TournamentChannelJDO, TournamentChannel, CreateTournamentChannel> {


    @Override
    public TournamentChannelJDO convert(CreateTournamentChannel creater) {
        if (creater == null) {
            return null;
        }

        TournamentChannelJDO jdo = new TournamentChannelJDO();

        jdo.setKey(KeyFac.createTournamentChannelKey(creater.getTournamentId()));
        jdo.setTournamentKey(KeyFac.convert(creater.getTournamentId()));

        return jdo;
    }

    @Override
    public TournamentChannel convert(TournamentChannelJDO jdo) {
        if (jdo == null) {
            return null;
        }

        TournamentChannel tc = new TournamentChannel();
        tc.setId(KeyFac.getTournamentChannelId(jdo.getKey()));
        tc.setClients(jdo.getClients());
        tc.setCreatedDate(jdo.getCreatedDate());
        tc.setLastChangeDate(jdo.getLastChangeDate());
        return tc;
    }

    @Override
    public void updateJDO(TournamentChannelJDO jdo, TournamentChannel model) {
        jdo.setClients(model.getClients());
    }
}