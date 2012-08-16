package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.gui.main.club.home.*;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class StatisticsResultTournamentPageController extends PageController<StatisticsResultTournamentPage> {

    public static String HISTORY_NAME = "club_StatisticsResultTournament";
    private static StatisticsResultTournamentPageController instance;


    private StatisticsResultTournamentPageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static StatisticsResultTournamentPageController getInstance() {
        if (instance == null) {
            instance = new StatisticsResultTournamentPageController();
        }
        return instance;
    }

    public StatisticsResultTournamentPage newInstance() {
        return new StatisticsResultTournamentPage();
    }

    public void afterLoad(){
        if(getPage().getSubPage() == null){
            PageFlow.show(ResultsPageController.getInstance());   
        }
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Statistics.png","Archive, Results and Statistics");

    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}