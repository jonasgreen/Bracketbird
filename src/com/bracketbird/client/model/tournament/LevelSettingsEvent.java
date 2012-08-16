package com.bracketbird.client.model.tournament;


/**
 *
 */
public class LevelSettingsEvent extends TournamentEvent {

    private LevelSettings settings;

    protected LevelSettingsEvent(LevelSettings ls, boolean fromClient) {
        super(fromClient);
        this.settings = ls;
    }

    public LevelSettings getSettings() {
        return settings;
    }

    public void setSettings(LevelSettings settings) {
        this.settings = settings;
    }
}