package com.bracketbird.client.pages.matches;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.gui.rtc.event.StateHandler;
import com.bracketbird.client.model.tournament.GroupStage;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Stage;
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

        stage.stateHandlers.addHandler(new StateHandler() {
            @Override
            public void onChange(StateChangedEvent event) {
                handleStateChange(event);
            }
        });


        if (stage.isGroupStage()) {
            stage.stateHandlers.addHandler(new StateHandler() {
                @Override
                public void onChange(StateChangedEvent event) {
                    getRankingPanel().setVisible(event.getNewState().isBeyondInProgress());
                }
            });
            add(getRankingPanel());
        }
    }

    private void handleStateChange(StateChangedEvent event) {
        //StateCrossing crossing = event.crosses(LevelState.ready);

        if (event.getOldState().isNotReady()) {
            matchesPanelHolder.clear();
            matchesPanelHolder.add(new MatchesTablePanel(stage));
        } else if (event.getNewState().isNotReady()) {
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
