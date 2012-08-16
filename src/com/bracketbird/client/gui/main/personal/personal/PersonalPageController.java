package com.bracketbird.client.gui.main.personal.personal;

import com.bracketbird.client.*;
import com.bracketbird.client.gui.main.AppPageController;
import com.bracketbird.client.gui.main.MainPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class PersonalPageController extends PageController<PersonalPage> {

    public static String HISTORY_NAME = "homepage";
    private static PersonalPageController instance;
    private boolean firstLoad = true;

    private PersonalPageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
    }

    public static PersonalPageController getInstance() {
        if (instance == null) {
            instance = new PersonalPageController();
        }
        return instance;
    }

    public PersonalPage newInstance() {
        return new PersonalPage();
    }

    public void afterLoadFromHistory() {
        afterLoad();
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("User.png", "Your page");
    }


    public void afterLoad() {
        if (firstLoad) {
           loadTournaments();
            //updateClubs();
            firstLoad = false;
        }
    }

    private void loadTournaments() {
        //getPage().getTournamentsPanel().loadTournaments();
    }

    public boolean makeHistory() {
        return false;
    }



    public void updateClubs() {
        BBService.findClubsByUser(UserManager.getInstance().getUser(), new CallBack<ListClubsResult>() {
            @Override
            public void success(ListClubsResult result) {
               // getPage().updateClubs(result.getClubs());
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_IN;
    }

    public void clear() {
        super.clear();
        firstLoad = true;
    }

}