package com.bracketbird.client.pages.matches;

import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.tournament.GroupStage;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.user.client.ui.FlowPanel;

public class StagePanel extends FlowPanel{

    private Stage stage;

    private MatchesTablePanel matchesTablePanel;
    private GroupStageRankingPanel rankingPanel;

    public StagePanel(Stage stage) {
        this.stage = stage;

        add(getMatchesTablePanel());

        if(stage.isGroupStage()){
            stage.stateHandlers.addHandler(new ModelEventHandler<LevelState>() {
                @Override
                public void handleEvent(ModelEvent<LevelState> event) {
                    getRankingPanel().setVisible(event.getAfter().isBeyondInProgress());
                }
            });

            add(getRankingPanel());
        }
    }

    public MatchesTablePanel getMatchesTablePanel() {
        if (matchesTablePanel == null) {
            matchesTablePanel = new MatchesTablePanel(stage);
        }
        return matchesTablePanel;
    }

    public GroupStageRankingPanel getRankingPanel() {
        if (rankingPanel == null) {
            rankingPanel = new GroupStageRankingPanel((GroupStage) stage);
        }
        return rankingPanel;
    }



    public Stage getStage() {
        return stage;
    }


}
