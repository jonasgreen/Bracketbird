package com.bracketbird.client.pages.settings;


import com.bracketbird.clientcore.appcontrol.Page;


/**
 *
 */
public class SettingsPage extends Page<SettingsPageController> {


    private LevelPanel levelPanel;

    public SettingsPage() {
    }

    public void init() {
        setStyleName("settingsPage");
        add(getLevelPanel());

    }


    public LevelPanel getLevelPanel() {
        if (levelPanel == null) {
            levelPanel = new LevelPanel();
        }
        return levelPanel;
    }





}
