package com.bracketbird.client.gui.main.personal.help;


import com.bracketbird.client.gui.main.MainPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class HelpPageController extends PageController<HelpPage> {


    public static String HISTORY_NAME = "HelpPage";
    private static HelpPageController instance;


    private HelpPageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static HelpPageController getInstance() {
        if (instance == null) {
            instance = new HelpPageController();
        }
        return instance;
    }

    public HelpPage newInstance() {
        return new HelpPage();

    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Question4.png", "Help");
    }

    public boolean makeHistory() {
        return true;
    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_IN;
    }
}