package com.bracketbird.client;


import com.bracketbird.client.gui.main.ErrorPageController;
import com.bracketbird.client.pages.front.FrontPageController;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.service.CallBack;
import com.bracketbird.clientcore.util.GlobalKeyboardHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

/**
 * Entry point
 * classes define <code>onModuleLoad()</code>.
 */

public class Bracketbird extends com.bracketbird.clientcore.appcontrol.AppEntry {


    protected GlobalKeyboardHandler gh = new GlobalKeyboardHandler();

    public static String tournamentUrl = "";

    public void onModuleLoad() {

        String token = History.getToken();
        History.newItem("");

        Window.enableScrolling(true);
        //PageFlow.activeController = AppPageController.getInstance();

        //UserManager.getInstance().silentLogIn();
        gh.setupSingleAppKeyboardShortcuts();


        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                if ("".equals(History.getToken())) {
                    History.newItem(tournamentUrl, false);
                }
                else {
                    loadPage(History.getToken());
                }

            }
        });

        loadPage(token);
    }

    private void loadPage(String token) {
        if (token == null || token.isEmpty()) {
            Application.show(FrontPageController.getInstance());
        }
        else {
            loadTournamentPage(token);
        }
    }



    private void loadErrorPage(String token) {
        ErrorPageController.get().tokenError(token);
        Application.show(ErrorPageController.get());
    }

    private void loadTournamentPage(final String token) {
        BBService.getTournament(token, true, new CallBack<TournamentResult>() {
            @Override
            public void success(TournamentResult r) {
                if (r.getTournamentId() == null) {
                    loadErrorPage(token);
                }
                else {
                    RTC.getInstance().loadTournament(r, r.getEventLogs(), false);
                }
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private boolean isMobile(){
        return Window.Navigator.getAppVersion().contains("Mobile");
    }

}
