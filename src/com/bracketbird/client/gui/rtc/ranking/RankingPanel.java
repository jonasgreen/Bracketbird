package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.clientcore.gui.FlowComponent;

/**
 *
 */
public interface RankingPanel {
    public void relayout();
    public Stage getLevel();
    public FlowComponent getContent();
}
