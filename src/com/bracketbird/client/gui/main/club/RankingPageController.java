package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class RankingPageController extends PageController<RankingPage> {

    public static String HISTORY_NAME = "club_Ranking";
    private static RankingPageController instance;


    private RankingPageController() {
        super(StatisticsResultTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static RankingPageController getInstance() {
        if (instance == null) {
            instance = new RankingPageController();
        }
        return instance;
    }

    public RankingPage newInstance() {
        return new RankingPage();
    }

    public void afterLoad(){
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuSimpleLinkComponent("Ranking");

    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}