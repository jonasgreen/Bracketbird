package com.bracketbird.client.gui.rtc.ranking;


import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuImageAndTextComponent;

/**
 *
 */
public class RankingViewPageController extends PageController<RankingViewPage> {

    private static RankingViewPageController instance;


    private RankingViewPageController() {
    }

    public static RankingViewPageController getInstance() {
        if (instance == null) {
            instance = new RankingViewPageController();
        }
        return instance;
    }


    public void afterLoad() {
        /*if(getPage().getRankingPanel() != null){
            getPage().getRankingPanel().relayout();
        }
        */
    }

    public RankingViewPage newInstance() {
        return new RankingViewPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("img/Presentation.png", "Presentation view");
    }
}