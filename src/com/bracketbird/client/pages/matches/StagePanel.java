package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.GroupStage;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.rtc.event.UpdateEvent;
import com.bracketbird.client.rtc.event.UpdateHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class StagePanel extends FlowPanel {

    private Stage stage;

    private SimplePanel matchesPanelHolder = new SimplePanel();
    private GroupStageRankingPanel rankingPanel;

    public StagePanel(Stage stage) {
        this.stage = stage;

        add(matchesPanelHolder);

        if (stage.getState().isAbove(LevelState.notReady)) {
            matchesPanelHolder.add(new MatchesTablePanel(stage));
        } else {
            matchesPanelHolder.add(new LevelEmptyPanel(stage));
        }

        stage.addStateHandler(new UpdateHandler<LevelState>() {
            @Override
            public void onUpdate(UpdateEvent<LevelState> event) {
                handleStateChange(event);
            }
        });


        if (stage.isGroupStage()) {
            stage.addStateHandler(new UpdateHandler<LevelState>() {
                @Override
                public void onUpdate(UpdateEvent<LevelState> event) {
                    getRankingPanel().setVisible(event.getNewValue().isBeyondInProgress());
                }
            });
            add(getRankingPanel());
        }
    }

    private void handleStateChange(UpdateEvent<LevelState> event) {
        //StateCrossing crossing = event.crosses(LevelState.ready);

        if (event.getOldValue().isNotReady()) {
            matchesPanelHolder.clear();
            matchesPanelHolder.add(new MatchesTablePanel(stage));
        } else if (event.getNewValue().isNotReady()) {
            matchesPanelHolder.clear();
            matchesPanelHolder.add(new LevelEmptyPanel(stage));
        }
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
