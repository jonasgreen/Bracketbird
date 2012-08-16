package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.clientcore.gui.FlowComponent;

/**
 *
 */
public interface RankingPanel {
    public void relayout();
    public TournamentLevel getLevel();
    public FlowComponent getContent();
}
