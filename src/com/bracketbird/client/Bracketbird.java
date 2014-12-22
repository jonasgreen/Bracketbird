package com.bracketbird.client;


import com.bracketbird.client.appcontrol.AppEntry;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.pages.front.FrontPageController;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.client.appcontrol.Application;
import com.bracketbird.client.util.GlobalKeyboardHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point
 * classes define <code>onModuleLoad()</code>.
 */

public class Bracketbird extends AppEntry {


    protected GlobalKeyboardHandler gh = new GlobalKeyboardHandler();

    public static String tournamentUrl = "";

    public void onModuleLoad() {
        setupExceptionHandling();


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

    private void setupExceptionHandling() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            @Override
            public void onUncaughtException(Throwable e) {
                Printer.printException(e);
            }
        });
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
        //TODO
    }

    private void loadTournamentPage(final String token) {
        BBService.getTournament(token, new AsyncCallback<TournamentResult>() {
            @Override
            public void onSuccess(TournamentResult r) {
                if (r.getTournamentId() == null) {
                    loadErrorPage(token);
                }
                else {
                    RTC.getInstance().loadTournament(r, r.getEventLogs(), false);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Printer.printException(t);
            }
        });


    }

    private boolean isMobile(){
        return Window.Navigator.getAppVersion().contains("Mobile");
    }

}
