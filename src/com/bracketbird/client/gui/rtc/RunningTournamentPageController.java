package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.gui.main.AppPageController;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuLinkComponent;

/**
 *
 */
public class RunningTournamentPageController extends PageController<RunningTournamentPage> {

    private static RunningTournamentPageController instance;
    public static String HISTORY_NAME = "editresults";


    private RunningTournamentPageController() {
        super(AppPageController.getInstance(), HISTORY_NAME);
    }

    public static RunningTournamentPageController getInstance() {
        if (instance == null) {
            instance = new RunningTournamentPageController();
        }
        return instance;
    }


    public MenuComponent getMenu() {
        return null;
    }

    public RunningTournamentPage newInstance() {
        return new RunningTournamentPage();
    }

    public boolean makeHistory() {
        return true;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("");
    }

}