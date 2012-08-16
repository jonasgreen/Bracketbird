package com.bracketbird.client.gui.main.club.tournament;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class TournamentInfoPageController extends PageController<TournamentInfoPage> {


    public static String HISTORY_NAME = "TournamentInfoPage";
    private static TournamentInfoPageController instance;

    private TournamentInfoPageController() {
        super(TournamentsPageController.getInstance(), HISTORY_NAME);
    }

    public static TournamentInfoPageController getInstance() {
        if (instance == null) {
            instance = new TournamentInfoPageController();
        }
        return instance;
    }


    public TournamentInfoPage newInstance() {
        return new TournamentInfoPage();

    }

    public void setFocus() {
        getPage().setFocus();
    }

    public void afterLoadFromHistory() {
        setFocus();
    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("Info");

    }

    public void afterLoad() {
        setFocus();

    }


    public boolean makeHistory() {
        return true;
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}