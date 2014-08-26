package com.bracketbird.client.pages.teamspage;


import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuImageAndTextComponent;

/**
 *
 */
public class TeamsPageController extends PageController<TeamsPage> {

    private static TeamsPageController instance;


    private TeamsPageController() {
    }

    public static TeamsPageController getInstance() {
        if (instance == null) {
            instance = new TeamsPageController();
        }
        return instance;
    }

    public void afterLoad() {
       /* if (getPage().getTeamsTable().isEmpty()) {
            RTC.getInstance().createTeam();
        }
        getPage().setFocus(true);
        */
    }

    public TeamsPage newInstance() {
        return new TeamsPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("img/Teams.png", "Add teams");
    }

}