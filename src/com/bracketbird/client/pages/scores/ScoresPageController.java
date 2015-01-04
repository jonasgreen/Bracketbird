package com.bracketbird.client.pages.scores;

import com.bracketbird.client.appcontrol.PageController;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;

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


    @Override
    public void afterFirstLoad() {
        RTC.getInstance().getTournament().addStateHandler(new UpdateHandler<LevelState>() {
            @Override
            public void onUpdate(UpdateEvent<LevelState> event) {
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
        //Iterating backwards - If a stage exists in the correct state - return it.
        int i = RTC.getInstance().getTournament().getStages().size()-1;
        while (i >= 0){
            Stage stage = RTC.getInstance().getTournament().getStage(i--);
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
            showingStage = null;
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
