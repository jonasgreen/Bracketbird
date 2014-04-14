package com.bracketbird.client.gui.main;

import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuLinkComponent;

/**
 *
 */
public class MainPageController extends PageController<MainPage> {

    private static MainPageController instance;
    public static String HISTORY_NAME = "mainpage";


    private MainPageController() {
        super(AppPageController.getInstance(), HISTORY_NAME);
    }

    public static MainPageController getInstance() {
        if (instance == null) {
            instance = new MainPageController();
        }
        return instance;
    }


    public MenuComponent getMenu() {
        return null;
    }

    public MainPage newInstance() {
        return new MainPage();
    }

    public boolean makeHistory() {
        return true;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("");
    }


   
}