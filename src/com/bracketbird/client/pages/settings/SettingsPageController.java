package com.bracketbird.client.pages.settings;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.model.tournament.TournamentLevelEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.clientcore.appcontrol.PageController;

/**
 *
 */
public class SettingsPageController extends PageController<SettingsPage> {

    private static SettingsPageController instance;


    private SettingsPageController() {
        
    }

    public static SettingsPageController getInstance() {
        if (instance == null) {
            instance = new SettingsPageController();
        }
        return instance;
    }

    public void afterLoad() {}

    public void beforeUnload() {}

    @Override
    public void afterFirstLoad() {
        for (TournamentLevel level : RTC.getInstance().getTournament().getLevels()) {
            addLevel(level);
        }

        RTC.getInstance().getTournament().addLevelListener(new TournamentListener<TournamentLevelEvent>() {
            public void onChange(TournamentLevelEvent event) {
                if (event.getAction() == TournamentLevelEvent.LevelAction.create) {
                    addLevel(event.getLevel());
                } else if (event.getAction() == TournamentLevelEvent.LevelAction.delete) {
                    removeLevel(event.getLevel());
                }
            }
        });
    }


    private void removeLevel(TournamentLevel level) {
        getPage().getLevelPanel().removeLevel(level);
    }

    private void addLevel(TournamentLevel level) {
        getPage().getLevelPanel().addLevel(level);
    }


    public SettingsPage newInstance() {
        return new SettingsPage();
    }





}
