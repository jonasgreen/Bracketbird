package com.bracketbird.server.repository;

import com.bracketbird.client.gui.rtc.event.InitEvent;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.tournament.CreateTournament;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.service.FinderFac;
import com.bracketbird.clientcore.appcontrol.ApplicationException;
import com.bracketbird.server.dao.Dao;
import com.bracketbird.server.jdo.club.RTCEventJDO;
import com.bracketbird.server.jdo.club.TournamentJDO;
import com.google.appengine.api.datastore.Key;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class TournamentRepository extends Repository<TournamentJDO, Tournament, CreateTournament> {


    public TournamentRepository() {
        this(new Dao<TournamentJDO>(TournamentJDO.class), new TournamentConverter());
    }

    public TournamentRepository(Dao<TournamentJDO> dao, Converter<TournamentJDO, Tournament, CreateTournament> con) {
        super(dao, con);
    }

    public Class<Tournament> getRepositoryType() {
        return Tournament.class;
    }

    public TournamentConverter getConverter() {
        return (TournamentConverter) super.getConverter();
    }


    public void delete(Key key) {
        delete(KeyFac.getTournamentId(key));
    }


    private Dao<RTCEventJDO> getRTCDao() {
        return new Dao<RTCEventJDO>(RTCEventJDO.class);
    }

    public Tournament create(CreateTournament creater) throws ApplicationException {
        TournamentJDO jdo = getDao().create(getConverter().convert(creater));

        Tournament tournament = getConverter().convert(jdo);

        //createGroupMatch first event for RTCEventLog.
        REvent initEvent = new InitEvent(1L, tournament.getId());
        initEvent.setStateId(0L);
        RTCEventJDO eventJdo = new RTCEventConverter().create(tournament.getId(), initEvent);
        getRTCDao().create(eventJdo);

        return tournament;
    }

    public List<REvent> getTournamentLog(TournamentId id) {
        Collection<RTCEventJDO> jdos = new Dao<RTCEventJDO>(RTCEventJDO.class).findBy(FinderFac.findByEntityId(id));
        return new RTCEventConverter().create(jdos);
    }
}