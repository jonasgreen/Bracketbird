package com.bracketbird.client.gui.rtc.help;

import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.UserStateConstant;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.*;

/**
 *
 */
public class AboutPageController extends PageController<AboutPage> {

    private static AboutPageController instance;
    public static String HISTORY_NAME = "aboutPage";


    private AboutPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static AboutPageController getInstance() {
        if (instance == null) {
            instance = new AboutPageController();
        }
        return instance;
    }

    public void afterLoad() {
    }

    public AboutPage newInstance() {
        return new AboutPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("help.png", "Help");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }




}