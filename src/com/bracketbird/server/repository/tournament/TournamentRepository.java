package com.bracketbird.server.repository.tournament;

import com.bracketbird.client.gui.rtc.event.InitEvent;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.server.repository.*;
import com.google.appengine.api.datastore.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

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




    public boolean exist(ClubId clubId, String tournamentName) {
        Finder f = FinderFac.findByClubIdAndName(clubId, tournamentName);
        return !getDao().findBy(f).isEmpty();
    }

    private Collection<PlayingTeamJDO> persist(TournamentId tId, List<Team> pTeams) {
        Dao<PlayingTeamJDO> teamDao = getPlTeamDao();

        //first delete all
        Collection<PlayingTeamJDO> teamsToDelete = teamDao.findBy(FinderFac.findByEntityId(tId));
        teamDao.deleteAll(teamsToDelete);

        //then create new
        PlayingTeamConverter conv = new PlayingTeamConverter();
        List<PlayingTeamJDO> teamJdos = conv.convertAll(tId, pTeams);


        return teamDao.createAll(teamJdos);
    }



    public void delete(EntityId id) {

        //delete subtournaments
        getSubDao().deleteAll(getSubtournaments((TournamentId) id));

        //delete playing teams
        getPlTeamDao().deleteAll(getPlayingTeams((TournamentId) id));

        //delete tournament
        getDao().delete(id);
    }

    public void delete(Key key) {
        delete(KeyFac.getTournamentId(key));
    }

/*
    public Tournament updateStart(TournamentId tId, List<PlayingTeam> teams, StartSettings settings, Boolean proceed) {
        TournamentConverter tConv = new TournamentConverter();
        PlayingTeamConverter teamConv = new PlayingTeamConverter();

        //read data
        TournamentJDO tJdo = getDao().read(tId);
        List<TournamentLevelJDO> subJdoTournaments = CollectionUtil.convert(getSubDao().findBy(FinderFac.findByEntityId(tId)));
        TournamentLevelJDO startJdoTournament = subJdoTournaments.remove(0);

        //persist new teams
        Collection<PlayingTeamJDO> teamList = persist(tId, teams);

        //collecting new teamskeys
        List<Key> teamKeys = new ArrayList<Key>();
        for (PlayingTeamJDO t : teamList) {
            teamKeys.add(t.getKey());
        }

        //setting teamkeys on tournament and TournamentStart
        startJdoTournament.setTeams(teamKeys);
        tJdo.setPlayingTeams(teamKeys);

        //setting new settings
        //tConv.updateStartSettings(startJdoTournament, settings);

        //update tournament and start sub
        Tournament tournament = conv.convert(getDao().update(tJdo));
        subJdoTournaments.add(0, getSubDao().update(startJdoTournament));


        //TODO - seeding
        if (proceed) {
            //indicates change in state
            startJdo.setEndingTeams(startJdo.getStartingTeams());
        }

        TournamentConverter conv = new TournamentConverter();



        //updating string values on Tournament for fast search of participaters
        tJdo.getTeams().clear();
        for (PlayingTeam pTeam : startJdo.getStartingTeams()) {
            tJdo.getTeams().add(pTeam.getAsStringValue());
        }

        //clear all non-start subtournaments.
        int index = 0;
        for (SubtournamentJDO stTemp : tJdo.getSubtournaments()) {
            if (index > 0) {
                stTemp.getMatches().clear();
                stTemp.getEndingTeams().clear();
                stTemp.getStartingTeams().clear();
            }
        }


        //setting relations on model.
        tournament.setPlayingTeams(teamConv.convertJDOs(teamList));
        tournament.setSubtournaments(new TournamentConverter().convertSubJDOS(subJdoTournaments));

        return tournament;
    }
*/

    //overwrite this one

    protected Tournament addRelations(Tournament t, TournamentJDO jdo) {
        //setting playing teams
        PlayingTeamConverter conv = new PlayingTeamConverter();
        Collection<PlayingTeamJDO> teams = getPlayingTeams(t.getId());
        t.setTeams(conv.convertJDOs(teams));

        Collection<TournamentLevelJDO> jdoTournaments = getSubtournaments(t.getId());
        t.setLevels(new TournamentConverter().convertSubJDOS(t, jdoTournaments));

        return t;
    }

    private Collection<TournamentLevelJDO> getSubtournaments(TournamentId id) {
        return getSubDao().findBy(FinderFac.findByEntityId(id));
    }

    private Collection<PlayingTeamJDO> getPlayingTeams(TournamentId id) {
        return getPlTeamDao().findBy(FinderFac.findByEntityId(id));
    }

    private Dao<TournamentLevelJDO> getSubDao() {
        return new Dao<TournamentLevelJDO>(TournamentLevelJDO.class);
    }


    private Dao<PlayingTeamJDO> getPlTeamDao() {
        return new Dao<PlayingTeamJDO>(PlayingTeamJDO.class);
    }


    private Dao<RTCEventJDO> getRTCDao() {
        return new Dao<RTCEventJDO>(RTCEventJDO.class);
    }

    public Tournament create(CreateTournament creater) throws ApplicationException {
        TournamentJDO jdo = getDao().create(getConverter().convert(creater));

        Tournament tournament = getConverter().convert(jdo);

        //create first event for RTCEventLog.
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