package com.bracketbird.client.applicationstarter;

import com.bracketbird.client.HistorySupport;
import com.bracketbird.client.UserManager;
import com.bracketbird.client.browser.Browser;
import com.bracketbird.client.gui.main.AppPageController;
import com.bracketbird.client.gui.main.FrontPageController;
import com.bracketbird.client.gui.main.personal.personal.CreateTournamentPageController;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RunningTournamentTop;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.bracketbird.clientcore.gui.PopupManager;
import com.bracketbird.clientcore.model.keys.UserId;
import com.google.gwt.user.client.Window;

/**
 *
 */
public class OpenTournamentStarter extends ApplicationStarter {

    private String tournamentUrlId;

    public OpenTournamentStarter(String tournamentUrlId) {
        this.tournamentUrlId = tournamentUrlId == null ? null : tournamentUrlId.trim();
    }

    public void start() {
        HistorySupport.getInstance().setAllowHistory(false);
        Window.enableScrolling(true);

        if (Browser.isIEBrowser()) {
            showIENotSupportedMessage();
            return;
        }

        if (tournamentUrlId.length() > 1) {
            loadTournament();
        }
        else {
            createTournament();
        }
        //UserManager.getInstance().silentLogIn();
        gh.setupSingleAppKeyboardShortcuts();


    }

    private void createTournament() {
        PageFlow.activeController = AppPageController.getInstance();
        String cookie = UserManager.getInstance().getUserIdFromCookie();
        CreateTournamentPageController.getInstance().setUserId(cookie == null ? null : new UserId(cookie));
        PopupManager.setLightRootPanel();
        PopupManager.show(CreateTournamentPageController.getInstance());
    }

    private void loadTournament() {
    }

    public static void showTournament(TournamentResult r) {
        RTC.getInstance().loadTournament(r.getTournament(), r.getEventLogs(), true);

        String tName = r.getTournament().getName();
        Window.setTitle(tName);
        RunningTournamentTop.getInstance().getTournamentName().setText(tName);

        RTC.getInstance().loadServerSync(r.getEventLogs(), true);
        PageFlow.show(TeamsPageController.getInstance());
        

        PopupManager.hide();
        PopupManager.setDarkRootPanel();
        com.google.gwt.user.client.History.newItem(r.getTournament().getUrl(), false);

    }

    public PageController getPageController() {
        return FrontPageController.getInstance();
    }

}
