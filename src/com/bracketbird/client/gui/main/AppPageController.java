package com.bracketbird.client.gui.main;

import com.bracketbird.client.model.UserStateConstant;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuLinkComponent;

/**
 *
 */
public class AppPageController extends PageController<AppPage> {

    private static AppPageController instance;
    public static String HISTORY_NAME = "apppage";


    private AppPageController() {
        super(null, HISTORY_NAME);
    }

    public static AppPageController getInstance() {
        if (instance == null) {
            instance = new AppPageController();
        }
        return instance;
    }


    public MenuComponent getMenu() {
        return null;
    }

    public AppPage newInstance() {
        return new AppPage();
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

   
}