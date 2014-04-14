package com.bracketbird.client.gui.main;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.google.gwt.user.client.Window;

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
        getPage().getContent().setWidth(Window.getClientWidth() + "px");

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


}
