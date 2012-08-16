package com.bracketbird.server.repository;

/**
 *
 */
public class RepFac {
    private static PlayingTeamRepository playingTeamRepository;


    public static PlayingTeamRepository getPlayingTeamRepository() {
        if (playingTeamRepository == null) {
            playingTeamRepository = new PlayingTeamRepository();
        }
        return playingTeamRepository;
    }
}
