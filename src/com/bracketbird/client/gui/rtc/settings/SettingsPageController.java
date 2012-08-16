package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class SettingsPageController extends PageController<SettingsPage> {

    private static SettingsPageController instance;
    public static String HISTORY_NAME = "SettingsPageController";
    private boolean firstTime = true;


    private SettingsPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static SettingsPageController getInstance() {
        if (instance == null) {
            instance = new SettingsPageController();
        }
        return instance;
    }

    public void afterLoad() {
        showSettingsFromScratch();
    }

    public SettingsPage newInstance() {
        return new SettingsPage();
    }

    public boolean makeHistory() {
        return false;
    }


    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Gear.png", "Change settings of tournament");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }

    public void showSettingsFromScratch() {
        //getPage().getSettingsSetupPanel().setVisible(false);
        getPage().getTournamentSettingsPanel().setVisible(true);
        if (firstTime) {
            firstTime = false;
            getPage().getTournamentSettingsPanel().showInitPopoup();
        }
    }


}