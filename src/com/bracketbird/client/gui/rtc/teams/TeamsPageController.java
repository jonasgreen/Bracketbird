package com.bracketbird.client.gui.rtc.teams;


import com.bracketbird.client.gui.rtc.OkWarning;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.resources.ext.ResourceGeneratorType;

/**
 *
 */
public class TeamsPageController extends PageController<TeamsPage> {

    private boolean firstShow = true;
    private static TeamsPageController instance;
    public static String HISTORY_NAME = "teamspage";


    private TeamsPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static TeamsPageController getInstance() {
        if (instance == null) {
            instance = new TeamsPageController();
        }
        return instance;
    }

    public void afterLoad() {
        if (getPage().getTeamsTable().isEmpty()) {
            RTC.getInstance().createTeam();
        }
        getPage().setFocus(true);
        /*if (firstShow && RTC.getInstance().getSync().getDoneEvents().size()< 5) {
            firstShow = false;
            OkWarning gc = new OkWarning("Don't forget to bookmark this tournament for later access");
            gc.setTitle("Bookmark this tournament");

            PopupManager.show(gc, new OnClose() {
                public void onClose() {
                    getPage().setFocus(true);
                }
            });
            gc.getOkButton().getButton().setFocus(true);
        }
        */

    }

    public TeamsPage newInstance() {
        return new TeamsPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Teams.png", "Add teams");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }


    public void hidePopups() {
        getPage().getInitPopup().hide();
    }
}