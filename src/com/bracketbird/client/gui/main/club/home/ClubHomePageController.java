package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class ClubHomePageController extends PageController<ClubHomePage> {

    public static String HISTORY_NAME = "club_ClubHome";
    private static ClubHomePageController instance;


    private ClubHomePageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static ClubHomePageController getInstance() {
        if (instance == null) {
            instance = new ClubHomePageController();
        }
        return instance;
    }

    public ClubHomePage newInstance() {
        return new ClubHomePage();
    }

    public void afterLoadFromHistory(){
        afterLoad();
    }

    public void afterLoad(){
        if (getChild() != null) {
            PageFlow.show(getChild());
        }
        else {
            PageFlow.show(ClubWallPageController.getInstance());
        }
    }



    public void setFocus(){
        if(getChild() != null){
            getChild().setFocus();
        }
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Home2.png","Home of your club");

    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}