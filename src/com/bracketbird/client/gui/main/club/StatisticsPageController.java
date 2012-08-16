package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class StatisticsPageController extends PageController<StatisticsPage> {

    public static String HISTORY_NAME = "club_Statistics";
    private static StatisticsPageController instance;


    private StatisticsPageController() {
        super(StatisticsResultTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static StatisticsPageController getInstance() {
        if (instance == null) {
            instance = new StatisticsPageController();
        }
        return instance;
    }

    public StatisticsPage newInstance() {
        return new StatisticsPage();
    }

    public void afterLoad(){
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuSimpleLinkComponent("Statistics");

    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}