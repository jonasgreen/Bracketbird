package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuImageAndTextComponent;

/**
 *
 */
public class EnterResultsPageController extends PageController<EnterResultsPage> {

    private static EnterResultsPageController instance;

    private EnterResultsPageController() {
    }

    public static EnterResultsPageController getInstance() {
        if (instance == null) {
            instance = new EnterResultsPageController();
        }
        return instance;
    }


    public void setFocus() {

    }

    public void afterLoad() {
    }

    public void beforeUnload() {
    }


    public EnterResultsPage newInstance() {
        return new EnterResultsPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("img/Play.png", "Start tournament");
    }



}