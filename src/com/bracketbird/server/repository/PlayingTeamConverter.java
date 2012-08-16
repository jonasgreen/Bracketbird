package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.appcontrol.*;

import java.util.*;


/**
 *
 */
public class PlayingTeamConverter extends Converter<PlayingTeamJDO, Team, CreatePlayingTeam> {


    public PlayingTeamJDO convert(CreatePlayingTeam model) {
       throw new SystemException("Method not implemented.");
    }

    public List<PlayingTeamJDO> convertAll(TournamentId tId, List<Team> ms){
        List<PlayingTeamJDO> jdos = new ArrayList<PlayingTeamJDO>();
        for (Team m : ms) {
            jdos.add(convert(tId, m));
        }
        return jdos;
    }

    public PlayingTeamJDO convert(TournamentId tId, Team model) {
        if (model == null) {
            return null;
        }

        PlayingTeamJDO jdo = new PlayingTeamJDO();

        jdo.setKey(model.getId().isLocal() ? KeyFac.createPlayingTeamKey(tId) : KeyFac.convert(model.getId()));
        jdo.setTournamentKey(KeyFac.convert(tId));
        //jdo.setMembers(KeyFac.convert(model.getMembers()));
        //jdo.setNonMembers(model.getNonMembers());
        jdo.setCreatedDate(model.getCreatedDate() == null ? new Date() : model.getCreatedDate());
        jdo.setName(model.getName());
        jdo.setInfo(model.getInfo());
        jdo.setSeeding(model.getSeeding());
        
        return jdo;
    }



    public Team convert(PlayingTeamJDO j) {
        if (j == null) {
            return null;
        }
        Team m = new Team();
        m.setId(KeyFac.getPlayingTeamId(j.getKey()));
        //m.setMembers(KeyFac.getMemberIds(j.getMembers()));
        //m.setNonMembers(j.getNonMembers());
        //m.setName(j.getName());
        //m.setInfo(j.getInfo());
        //m.setSeeding(j.getSeeding());
        m.setCreatedDate(j.getCreatedDate());
        return m;
    }

    @Override
    public void updateJDO(PlayingTeamJDO jdo, Team model) {
        throw new SystemException("updateJDO(Model m) not supported in " + this.getClass().getName());
    }


}