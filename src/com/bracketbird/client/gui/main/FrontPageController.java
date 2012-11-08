package com.bracketbird.client.gui.main;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class FrontPageController extends PageController<FrontPage> {

    private static FrontPageController instance;
    public static String HISTORY_NAME = "main";

    private FrontPageController() {
        super(AppPageController.getInstance(), HISTORY_NAME);
    }

    public static FrontPageController getInstance() {
        if (instance == null) {
            instance = new FrontPageController();

        }
        return instance;
    }



    public MenuComponent getMenu() {
        return null;
    }


    public void afterLoad() {
    }

    public void unload() {
    }

    public FrontPage newInstance() {
        return new FrontPage();
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
