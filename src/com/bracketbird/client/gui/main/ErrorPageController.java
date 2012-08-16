package com.bracketbird.client.gui.main;

import com.bracketbird.client.model.UserStateConstant;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuLinkComponent;

/**
 *
 */
public class ErrorPageController extends PageController<ErrorPage> {

    private static ErrorPageController instance;
    public static String HISTORY_NAME = "error";

    private ErrorPageController() {
        super(AppPageController.getInstance(), HISTORY_NAME);
    }

    public static ErrorPageController getInstance() {
        if (instance == null) {
            instance = new ErrorPageController();

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

    public ErrorPage newInstance() {
        return new ErrorPage();
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
