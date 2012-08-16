package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class MainPageController extends PageController<MainPage> {

    public static String HISTORY_NAME = "MainPage";
    private static MainPageController instance;
    private MenuLinkComponent ml = null;

    private MainPageController() {
        super(null, HISTORY_NAME);
    }

    public static MainPageController getInstance() {
        if (instance == null) {
            instance = new MainPageController();
        }
        return instance;
    }


    public MainPage newInstance() {
        return new MainPage();

    }

    public void afterLoadFromHistory() {

    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("");
    }

    public void afterLoad() {
    }

    public boolean makeHistory() {
        return true;
    }

    public MenuComponent getMenu() {
        return ml;
    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }


}