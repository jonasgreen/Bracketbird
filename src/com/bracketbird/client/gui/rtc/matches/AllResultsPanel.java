package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class AllResultsPanel extends VerticalComponent {

    private TournamentListener<TournamentLevelEvent> addLevelListener = new TournamentListener<TournamentLevelEvent>() {
        public void onChange(TournamentLevelEvent event) {
            if (event.getAction() == TournamentLevelEvent.LevelAction.create) {
                createPanel(event.getLevel());
            }
            else if (event.getAction() == TournamentLevelEvent.LevelAction.delete) {
                deletePanel();
            }
        }

    };


    private List<LevelResultPanel> levelResultPanels = new ArrayList<LevelResultPanel>();

    public AllResultsPanel() {
        super();
        init();
    }

    private void init() {
        RTC.getInstance().getTournament().addLevelListener(addLevelListener);

        final List<TournamentLevel> list = RTC.getInstance().getTournament().getLevels();
        if (levelResultPanels.size() < list.size()) {
            for (TournamentLevel l : list) {
                createPanel(l);
            }
        }

        add(FieldManager.getInstance());
    }

    private void createPanel(final TournamentLevel level) {
        LevelResultPanel lp = new LevelResultPanel(level, levelResultPanels.size() + 1);
        add(lp, new TextLayout(null, "100%"));
        levelResultPanels.add(lp);
    }

    private void deletePanel() {
        if (!levelResultPanels.isEmpty()) {
            LevelResultPanel rp = levelResultPanels.remove(levelResultPanels.size() - 1);
            rp.removeFromParent();
        }
    }
}
