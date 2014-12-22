package com.bracketbird.client.pages.settings;

import com.bracketbird.client.model.event.CreateDeleteHandler;
import com.bracketbird.client.model.event.CreateEvent;
import com.bracketbird.client.model.event.DeleteEvent;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.appcontrol.PageController;

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


    @Override
    public void afterFirstLoad() {
        for (Stage level : RTC.getInstance().getTournament().getStages()) {
            addLevel(level);
        }


        RTC.getInstance().getTournament().stagesDispatcher.addHandler(new CreateDeleteHandler<Stage>() {
            @Override
            public void onCreate(CreateEvent<Stage> event) {
                addLevel(event.getValue());
            }

            @Override
            public void onDelete(DeleteEvent<Stage> event) {
                removeLevel(event.getValue());
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
