package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class ClubInfoPageController extends PageController<ClubInfoPage> {

    public static String HISTORY_NAME = "ClubInfoPage";
    private static ClubInfoPageController instance;

    private ClubInfoPageController() {
        super(ClubHousePageController.getInstance(), HISTORY_NAME);
    }

    public static ClubInfoPageController getInstance() {
        if (instance == null) {
            instance = new ClubInfoPageController();
        }
        return instance;
    }


    public ClubInfoPage newInstance() {
        return new ClubInfoPage();

    }

    public void afterLoadFromHistory() {

    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("ClubInfo");

    }

    public void afterLoad() {
    }

    public boolean makeHistory() {
        return true;
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}