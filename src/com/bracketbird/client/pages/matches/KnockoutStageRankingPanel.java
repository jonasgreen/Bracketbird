package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.KnockoutStage;
import com.google.gwt.user.client.ui.Label;

public class KnockoutStageRankingPanel extends RankingPanel{

    private KnockoutStage stage;

    public KnockoutStageRankingPanel(KnockoutStage stage) {
       this.stage = stage;

        add(new Label("knockout stage ranking"));
    }



}
