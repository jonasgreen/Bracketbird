package com.bracketbird.client.gui.main.club;

import com.bracketbird.client.gui.main.club.home.*;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class AdminPageController extends PageController<AdminPage> {

    public static String HISTORY_NAME = "admin";
    private static AdminPageController instance;


    private AdminPageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static AdminPageController getInstance() {
        if (instance == null) {
            instance = new AdminPageController();
        }
        return instance;
    }

    public AdminPage newInstance() {
        return new AdminPage();
    }

    public void afterLoad(){
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Lock.png","Admin");

    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.ADMIN;
    }


}