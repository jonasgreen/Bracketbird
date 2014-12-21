package com.bracketbird.server.repository;

import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.server.jdo.TournamentJDO;
import com.bracketbird.server.util.RandomPass;
import com.google.appengine.api.datastore.Key;

import java.util.Date;


/**
 *
 */
public class TournamentConverter extends Converter<TournamentJDO, Tournament, CreateTournament> {


    public TournamentJDO convert(CreateTournament cr) {
        if (cr == null) {
            return null;
        }

        TournamentJDO jdo = new TournamentJDO();

        Key tKey = KeyFac.convert(cr.getTournamentId());
        jdo.setKey(tKey);
        jdo.setUrl(RandomPass.getUrl(4) + tKey.getId());
        jdo.setViewUrl(RandomPass.getUrl(4) + tKey.getId() + "v");

        jdo.setCreatedDate(new Date());
        jdo.setName(cr.getNameOfTournament());

        return jdo;
    }



    public Tournament convert(TournamentJDO jdo) {
        if (jdo == null) {
            return null;
        }
        Tournament t = new Tournament();
        t.setId(KeyFac.getTournamentId(jdo.getKey()));
        if(jdo.getTournamentChannelKey() != null){
            t.setTournamentChannelId(KeyFac.getTournamentChannelId(jdo.getTournamentChannelKey()));
        }
        t.setLastChangeDate(jdo.getLastChangeDate());
        t.setCreatedDate(jdo.getCreatedDate());
        t.setName(jdo.getName());
        t.setUrl(jdo.getUrl());
        t.setViewUrl(jdo.getViewUrl());
        return t;
    }

    @Override
    public void updateJDO(TournamentJDO jdo, Tournament model) {
        jdo.setName(model.getName());
        jdo.setTournamentChannelKey(model.getTournamentChannelId() != null ? KeyFac.convert(model.getTournamentChannelId()) : null);
    }


}