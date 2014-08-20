package com.bracketbird.client.gui.main;

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

    public static ErrorPageController get() {
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

    public void beforeUnload() {
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

    public void tokenError(String token){
        getPage().getHeader().setText("Ooops, unable to load page");
        getPage().getText().setText("Unable to load tournament with id = "+token+". Please verify that there is no typo in the address bar.");
    }

}
