package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.user.client.ui.FlowPanel;

public abstract class RankingPanel extends FlowPanel {

    private Stage stage;

    public RankingPanel(Stage stage) {
        this.stage = stage;
    }
}
