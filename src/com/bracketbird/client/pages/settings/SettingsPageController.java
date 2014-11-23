package com.bracketbird.client.pages.settings;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.tournament.Stage;
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

        for (Stage level : RTC.getInstance().getTournament().getStages()) {
            addLevel(level);
        }

        RTC.getInstance().getTournament().stagesEventHandlers.addHandler(new ModelEventHandler<Stage>() {
            @Override
            public void handleEvent(ModelEvent<Stage> event) {
                if (event.isCreate()) {
                    addLevel(event.getAfter());
                }
                else if (event.isDelete()) {
                    removeLevel(event.getBefore());
                }
            }
        });
    }


    private void removeLevel(Stage level) {
        getPage().getLevelPanel().removeLevel(level);
    }

    private void addLevel(Stage level) {
        getPage().getLevelPanel().addLevel(level);
    }


    public SettingsPage newInstance() {
        return new SettingsPage();
    }


}
