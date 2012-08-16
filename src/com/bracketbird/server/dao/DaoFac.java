package com.bracketbird.server.dao;

import com.bracketbird.server.jdo.club.PlayingTeamJDO;

/**
 *
 */
public class DaoFac {
    private static Dao<PlayingTeamJDO> playingTeamDao;


    public static Dao<PlayingTeamJDO> getPlayingTeamDao() {
        if (playingTeamDao == null) {
            playingTeamDao = new Dao<PlayingTeamJDO>(PlayingTeamJDO.class);
        }
        return playingTeamDao;
    }



}
