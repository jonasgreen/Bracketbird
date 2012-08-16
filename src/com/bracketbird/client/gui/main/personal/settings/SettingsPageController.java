package com.bracketbird.client.gui.main.personal.settings;

import com.bracketbird.client.gui.main.MainPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class SettingsPageController extends PageController<SettingsPage> {

    public static String HISTORY_NAME = "SettingsPage";
    private static SettingsPageController instance;

    private SettingsPageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static SettingsPageController getInstance() {
        if (instance == null) {
            instance = new SettingsPageController();
        }
        return instance;
    }


    public SettingsPage newInstance() {
        return new SettingsPage();

    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Gear.png", "Your settings");
    }


    public boolean makeHistory() {
        return true;
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_IN;
    }


}