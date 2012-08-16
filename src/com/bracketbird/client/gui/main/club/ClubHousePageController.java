package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.gui.main.club.home.*;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class ClubHousePageController extends PageController<ClubHousePage> {

    public static String HISTORY_NAME = "club";
    private static ClubHousePageController instance;


    private ClubHousePageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static ClubHousePageController getInstance() {
        if (instance == null) {
            instance = new ClubHousePageController();
        }
        return instance;
    }

    public ClubHousePage newInstance() {
        return new ClubHousePage();
    }


    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


    public boolean makeHistory() {
        return false;
    }
}