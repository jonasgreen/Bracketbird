package com.bracketbird.client.pages.scores;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.gui.rtc.event.StateHandler;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.clientcore.appcontrol.PageController;

/**
 *
 */
public class ScoresPageController extends PageController<ScoresPage> {

    private static ScoresPageController instance;
    private Stage showingStage;

    private ScoresPageController() {
    }

    public static ScoresPageController getInstance() {
        if (instance == null) {
            instance = new ScoresPageController();
        }
        return instance;
    }

    public void afterLoad() {
    }

    public void beforeUnload() {
    }

    @Override
    public void afterFirstLoad() {
        RTC.getInstance().getTournament().addStateHandler(new StateHandler() {
            @Override
            public void onChange(StateChangedEvent event) {
                showPage();
            }
        });

        showPage();
    }

    private void showPage() {
        if (RTC.getInstance().getTournament().isFinished()) {
            showTournamentFinished();
        }
        else {
            show(getLastStageInAction());
        }
    }


    private Stage getLastStageInAction() {
        //Iterating backwards - If a stage exists in the correct state - show it.
        int i = RTC.getInstance().getTournament().getStages().size()-1;
        while (i >= 0){
            Stage stage = RTC.getInstance().getTournament().getStage(i);
            if(stage.getState().isAboveOrEquals(LevelState.ready)){
                return stage;
            }
        }
        return null;
    }


    private void showTournamentFinished() {
        this.showingStage = null;
        getPage().showTournamentFinished(RTC.getInstance().getTournament());
    }

    private void show(Stage stage) {
        if(stage == null){
            getPage().showNothing();
        }
        else if(!stage.equals(showingStage)){
            showingStage = stage;
            getPage().show(stage);
        }
    }


    public ScoresPage newInstance() {
        return new ScoresPage();
    }


}
