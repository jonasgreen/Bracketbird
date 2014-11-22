package com.bracketbird.client.pages.settings;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.tournament.TournamentStage;
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

    public void afterLoad() {
    }

    public void beforeUnload() {
    }

    @Override
    public void afterFirstLoad() {
        System.out.println("FIRST LOAD");

        for (TournamentStage level : RTC.getInstance().getTournament().getLevels()) {
            addLevel(level);
        }

        RTC.getInstance().getTournament().levelsEventHandlers.addHandler(new ModelEventHandler<TournamentStage>() {
            @Override
            public void handleEvent(ModelEvent<TournamentStage> event) {
                if (event.isCreate()) {
                    addLevel(event.getAfter());
                }
                else if (event.isDelete()) {
                    removeLevel(event.getBefore());
                }
            }
        });
    }


    private void removeLevel(TournamentStage level) {
        getPage().getLevelPanel().removeLevel(level);
    }

    private void addLevel(TournamentStage level) {
        getPage().getLevelPanel().addLevel(level);
    }


    public SettingsPage newInstance() {
        return new SettingsPage();
    }


}
