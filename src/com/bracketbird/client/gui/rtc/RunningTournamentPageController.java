package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.gui.main.AppPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

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

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }


    public void ejectIn() {
        getPage().ejectIn();
    }

    public void ejectOut() {
        getPage().ejectOut();
    }
}