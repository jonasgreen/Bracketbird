package com.bracketbird.client.pages.settingspage;


import com.bracketbird.clientcore.appcontrol.Page;


/**
 *
 */
public class SettingsPage extends Page<SettingsPageController> {

    public SettingsPage() {
    }

    public void init() {
        setStyleName("settingsPage");
        add(new LevelPanel());
    }



}
