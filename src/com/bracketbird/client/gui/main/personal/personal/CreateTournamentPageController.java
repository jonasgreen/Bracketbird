package com.bracketbird.client.gui.main.personal.personal;


import com.bracketbird.client.Bracketbird;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.gui.rtc.RunningTournamentTop;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.model.UserStateConstant;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.keys.UserId;
import com.bracketbird.clientcore.service.CallBack;
import com.google.gwt.user.client.Window;


/**
 *
 */
public class CreateTournamentPageController extends PageController<CreateTournamentPage> implements PopupPageController {

    private static CreateTournamentPageController instance = null;
    public static String HISTORY_NAME = "createtournament";

    private UserId userId;

    private CreateTournamentPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static CreateTournamentPageController getInstance() {
        if (instance == null) {
            instance = new CreateTournamentPageController();
        }
        return instance;
    }

    public CreateTournamentPage newInstance() {
        return new CreateTournamentPage();
    }

    public void afterLoad() {
        getPage();
        page.setVisible(true);
        page.getTextBox().setText("");
        page.getTextBox().setFocus(true);
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLabelComponent("");
    }

    public MenuLabelComponent getMenu() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void createTournament() {
        final String name = getPage().getTextBox().getText();
        if (name == null || name.equals("")) {
            return;
        }

        BBService.createTournament(name, userId, new CallBack<TournamentResult>() {
            @Override
            public void success(TournamentResult r) {
                RTC.getInstance().loadTournament(r.getTournament(), r.getEventLogs(), true);
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

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
