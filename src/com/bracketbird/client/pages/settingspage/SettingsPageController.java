package com.bracketbird.client.pages.settingspage;

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

    public SettingsPage newInstance() {
        return new SettingsPage();
    }



}
