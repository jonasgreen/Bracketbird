package com.bracketbird.server.repository;

import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class PlayingTeamRepository extends Repository<PlayingTeamJDO, Team, CreatePlayingTeam>{


    public PlayingTeamRepository(){
        this(new Dao<PlayingTeamJDO>(PlayingTeamJDO.class), new PlayingTeamConverter());
    }

    public PlayingTeamRepository(Dao<PlayingTeamJDO> dao, Converter<PlayingTeamJDO, Team, CreatePlayingTeam> con) {
        super(dao, con);
    }

    public Class<Team> getRepositoryType() {
        return Team.class;
    }


    public boolean exist(ClubId clubId, String tournamentName){
        Finder f = FinderFac.findByClubIdAndName(clubId, tournamentName);
        return !getDao().findBy(f).isEmpty();
    }

    






    
}