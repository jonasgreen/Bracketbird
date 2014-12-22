package com.bracketbird.client.pages.front;

import com.bracketbird.client.Printer;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.pages.teams.TeamsPageController;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.client.appcontrol.Application;
import com.bracketbird.client.appcontrol.PageController;
import com.bracketbird.client.appcontrol.TournamentContext;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
            BBService.createTournament(new AsyncCallback<TournamentResult>() {
                @Override
                public void onSuccess(TournamentResult r) {
                    instance.tournamentResult = r;
                    instance.handleTournamentCreation();
                }

                @Override
                public void onFailure(Throwable t) {
                    Printer.printException(t);
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
            RTC.getInstance().loadTournament(tournamentResult, tournamentResult.getEventLogs(), true);
            Application.show(TeamsPageController.getInstance());
        }
    }



    public FrontPage newInstance() {
        return new FrontPage();
    }

    public void createTournament() {
        createTournamentCalled = true;
        handleTournamentCreation();
    }
}
