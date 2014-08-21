package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuImageAndTextComponent;

/**
 *
 */
public class SettingsPageController extends PageController<SettingsPage> {

    private static SettingsPageController instance;
    private boolean firstTime = true;


    private SettingsPageController() {
    }

    public static SettingsPageController getInstance() {
        if (instance == null) {
            instance = new SettingsPageController();
        }
        return instance;
    }

    public void afterLoad() {
        //showSettingsFromScratch();
    }

    public SettingsPage newInstance() {
        return new SettingsPage();
    }

    public boolean makeHistory() {
        return false;
    }


    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("img/Gear.png", "Change settings of tournament");
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