package com.bracketbird.client.gui.rtc.ranking;


import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class RankingViewPageController extends PageController<RankingViewPage> {

    private static RankingViewPageController instance;
    public static String HISTORY_NAME = "RankingViewPageController";


    private RankingViewPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static RankingViewPageController getInstance() {
        if (instance == null) {
            instance = new RankingViewPageController();
        }
        return instance;
    }


    public void afterLoad() {
        if(getPage().getRankingPanel() != null){
            getPage().getRankingPanel().relayout();
        }
    }

    public RankingViewPage newInstance() {
        return new RankingViewPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Presentation.png", "Presentation view");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }
}