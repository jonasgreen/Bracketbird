package com.bracketbird.client.pages.settingspage;


import com.bracketbird.clientcore.appcontrol.Page;
import com.google.gwt.user.client.ui.Label;


/**
 *
 */
public class SettingsPage extends Page<SettingsPageController> {

    public SettingsPage() {
    }

    public void init() {
        setStyleName("settingsPage");
        add(new LevelComponent("Begin"));
        addSep();
        add(new LevelComponent("Group"));
        addSep();
        add(new LevelComponent("End"));

    }

    private void addSep() {
        Label l = new Label();
        l.setStyleName("settingsPage_sep");
        l.setText(">>");
        add(l);
    }


}
