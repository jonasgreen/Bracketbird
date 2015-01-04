package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.bracketbird.client.model.tournament.GroupStage;
import com.bracketbird.client.model.tournament.KnockoutStage;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class StagePanel extends FlowPanel {

    private Stage stage;

    private LayoutMatchesButtonPanel layoutMatchesPanel;
    private MatchesTablePanel matchesPanel;
    private RankingPanel rankingPanel;


    public StagePanel(Stage stage) {
        this.stage = stage;

        stage.addStateHandler(new UpdateHandler<LevelState>() {
            @Override
            public void onUpdate(UpdateEvent<LevelState> event) {
                updatePanels(event.getNewValue());
            }
        });

        updatePanels(stage.getState());
    }

    private void updatePanels(LevelState state) {
        removeFromParent(layoutMatchesPanel);

        if(state.isNotReady()){
            removeFromParent(matchesPanel);
            matchesPanel = null;

            removeFromParent(rankingPanel);
            rankingPanel = null;

            layoutMatchesPanel = new LayoutMatchesButtonPanel(stage);
            add(layoutMatchesPanel);
        }
        else {
            if(matchesPanel == null){
                matchesPanel = new MatchesTablePanel(stage);
                add(matchesPanel);
            }

            if(state.isAbove(LevelState.inProgress)){
                if(rankingPanel == null){
                    rankingPanel = createRankingPanel();
                    add(rankingPanel);
                }
            }
            else{
                if(rankingPanel != null){
                    rankingPanel.removeFromParent();
                    rankingPanel = null;
                }
            }


        }




        //new MatchesTablePanel(stage);



    }

    private RankingPanel createRankingPanel() {
        return stage instanceof GroupStage ? new GroupStageRankingPanel((GroupStage) stage) : new KnockoutStageRankingPanel((KnockoutStage) stage);
    }

    private void removeFromParent(Widget w) {
        if(w != null){
            w.removeFromParent();
        }
    }


    public Stage getStage() {
        return stage;
    }


}
