package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.TournamentStage;
import com.bracketbird.clientcore.gui.FlowComponent;

/**
 *
 */
public interface RankingPanel {
    public void relayout();
    public TournamentStage getLevel();
    public FlowComponent getContent();
}
