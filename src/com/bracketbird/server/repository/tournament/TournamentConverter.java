package com.bracketbird.server.repository.tournament;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.url.UrlUtil;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.server.util.RandomPass;
import com.google.appengine.api.datastore.*;
import com.bracketbird.clientcore.model.keys.*;

import java.util.*;


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

/*
    private List<TournamentLevel> convertJDOs(List<TournamentLevelJDO> jdoTournaments) {
        List<TournamentLevel> retList = new ArrayList<TournamentLevel>();
        for (TournamentLevelJDO jdoTournament : jdoTournaments) {
            retList.add(convert(jdoTournament));
        }
        return retList;
    }

  */

    public TournamentLevel convert(Tournament t, TournamentLevelJDO jdo) {
        TournamentLevel model = TournamentLevelFac.create(t, jdo.getType());

        model.setId(KeyFac.getSubtournamentId(jdo.getKey()));
        model.setCreatedDate(jdo.getCreatedDate());
        model.setLastChangeDate(jdo.getLastChangeDate());
        model.setTournamentId(KeyFac.getTournamentId(jdo.getTournamentKey()));

        //model.setTeams(convertPlayingTeamKeys(jdo.getTeams()));
        LevelSettings ss = new LevelSettings();
        ss.setEliminationType(jdo.getEliminationType());
        ss.setMaxNumberOfTeams(jdo.getMaxNumberOfTeams());
        ss.setNumberOfMatches(jdo.getNumberOfMatches());
        ss.setNumberOfGroups(jdo.getNumberOfGroups());
        ss.setNumberOfRepeats(jdo.getNumberOfRepeats());
        ss.setPointsOfDraw(jdo.getPointsOfDraw());
        ss.setPointsOfVictory(jdo.getPointsOfVictory());
        ss.setRankingRules(jdo.getRankingRules());

        model.setStageSettings(ss);
        //TODO - matches
        return model;
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

    public List<TournamentLevel> convertSubJDOS(Tournament t, Collection<TournamentLevelJDO> jdos) {
        List<TournamentLevel> list = new ArrayList<TournamentLevel>();
        for (TournamentLevelJDO jdo : jdos) {
            list.add(convert(t, jdo));
        }
        return list;
    }

    public void updateJDO(TournamentLevelJDO jdo, TournamentLevel model) {
        jdo.setEliminationType(model.getStageSettings().getEliminationType());
        jdo.setMaxNumberOfTeams(model.getStageSettings().getMaxNumberOfTeams());
        jdo.setNumberOfMatches(model.getStageSettings().getNumberOfMatches());
        jdo.setNumberOfGroups(model.getStageSettings().getNumberOfGroups());
        jdo.setNumberOfRepeats(model.getStageSettings().getNumberOfRepeats());
        jdo.setPointsOfDraw(model.getStageSettings().getPointsOfDraw());
        jdo.setPointsOfVictory(model.getStageSettings().getPointsOfVictory());
        jdo.setRankingRules(model.getStageSettings().getRankingRules());
        //jdo.setTeams(convertIds(model.getStageSettings().getTeams()));

        //jdo.setMatches(convert); TODO

    }


    public static List<Key> convertIds(List<TeamId> ids) {
        if (ids == null) {
            return null;
        }
        List<Key> keys = new ArrayList<Key>();
        for (EntityId id : ids) {
            keys.add(KeyFac.convert(id));
        }
        return keys;
    }


    public static List<TeamId> convertPlayingTeamKeys(List<Key> keys) {
        if (keys == null) {
            return null;
        }
        List<TeamId> ids = new ArrayList<TeamId>();
        for (Key key : keys) {
            ids.add(KeyFac.getPlayingTeamId(key));
        }
        return ids;
    }


}