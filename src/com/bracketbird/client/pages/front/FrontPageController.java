package com.bracketbird.client.pages.front;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.pages.teams.TeamsPageController;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.appcontrol.TournamentContext;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuLinkComponent;
import com.bracketbird.clientcore.service.CallBack;

/**
 *
 */
public class FrontPageController extends PageController<FrontPage> {

    private static FrontPageController instance;
    private TournamentResult tournamentResult;
    private boolean createTournamentCalled = false;


    private FrontPageController() {
    }

    public static FrontPageController getInstance() {
        if (instance == null) {
            instance = new FrontPageController();
            BBService.createTournament(new CallBack<TournamentResult>() {
                @Override
                public void success(TournamentResult r) {
                    instance.tournamentResult = r;
                    instance.handleTournamentCreation();
                }

                @Override
                public void fail(Throwable t) {
                    t.printStackTrace();
                }
            });

        }
        return instance;
    }

    private void handleTournamentCreation() {
        if(!createTournamentCalled){
            return;
        }
        if(tournamentResult == null){
            //TODO.
        }
        else{
            Application.get().shiftApplicationContext(TournamentContext.get());
            RTC.getInstance().loadTournament(tournamentResult.getTournament(), tournamentResult.getEventLogs(), true);
            Application.show(TeamsPageController.getInstance());
        }
    }


    public void afterLoad() {

    }

    public void beforeUnload() {
    }

    public FrontPage newInstance() {
        return new FrontPage();
    }

    public boolean makeHistory() {
        return true;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("");
    }


    public void createTournament() {
        createTournamentCalled = true;
        handleTournamentCreation();
    }
}
