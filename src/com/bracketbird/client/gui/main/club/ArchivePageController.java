package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class ArchivePageController extends PageController<ArchivePage> {

    public static String HISTORY_NAME = "club_archive";
    private static ArchivePageController instance;


    private ArchivePageController() {
        super(ClubHousePageController.getInstance(), HISTORY_NAME);
    }

    public static ArchivePageController getInstance() {
        if (instance == null) {
            instance = new ArchivePageController();
        }
        return instance;
    }

    public ArchivePage newInstance() {
        return new ArchivePage();
    }

    public void afterLoad(){
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLabelComponent("Archive");

    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}