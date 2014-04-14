package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;

/**
 *
 */
public class SeedingPageController extends PageController<SeedingPage> {

    private static SeedingPageController instance;
    public static String HISTORY_NAME = "SeedingPageController";


    private SeedingPageController() {
        super(TeamsPageController.getInstance(), HISTORY_NAME);
    }

    public static SeedingPageController getInstance() {
        if (instance == null) {
            instance = new SeedingPageController();
        }
        return instance;
    }


    public void setFocus() {

    }

    public void afterLoad() {
        getPage().getSeedingPanel().setInitialSeedings();
        getPage().setWidth("400px");
    }

    public void unload() {
    }


    public SeedingPage newInstance() {
        return new SeedingPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return null;
    }


}