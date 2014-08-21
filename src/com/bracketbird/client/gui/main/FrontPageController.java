package com.bracketbird.client.gui.main;

import com.bracketbird.client.pages.FrontPage;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuLinkComponent;

/**
 *
 */
public class FrontPageController extends PageController<FrontPage> {

    private static FrontPageController instance;

    private FrontPageController() {
    }

    public static FrontPageController getInstance() {
        if (instance == null) {
            instance = new FrontPageController();

        }
        return instance;
    }


    public void afterLoad() {

    }

    public void beforeUnload() {
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
