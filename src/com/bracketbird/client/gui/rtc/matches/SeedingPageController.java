package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;

/**
 *
 */
public class SeedingPageController extends PageController<SeedingPage> {

    private static SeedingPageController instance;


    private SeedingPageController() {
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
       // getPage().setWidth("400px");
    }

    public void beforeUnload() {
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