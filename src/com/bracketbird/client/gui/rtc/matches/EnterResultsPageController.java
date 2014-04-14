package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class EnterResultsPageController extends PageController<EnterResultsPage> {

    private static EnterResultsPageController instance;
    public static String HISTORY_NAME = "EnterResultsPageController";


    private EnterResultsPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
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

    public void unload() {
    }


    public EnterResultsPage newInstance() {
        return new EnterResultsPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Play.png", "Start tournament");
    }



}