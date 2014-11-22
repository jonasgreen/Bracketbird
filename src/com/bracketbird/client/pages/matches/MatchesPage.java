package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.tournament.TournamentStage;
import com.bracketbird.clientcore.appcontrol.Page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class MatchesPage extends Page<MatchesPageController> {

    private List<LevelMatchesPanel> levelMatchesPanels = new ArrayList<LevelMatchesPanel>();

    @Override
    protected void init() {
        setStyleName("matchesPage");
    }


    public void createMatchesPanel(TournamentStage l) {
        LevelMatchesPanel lp = new LevelMatchesPanel(l, levelMatchesPanels.size() + 1);
        add(lp);
        levelMatchesPanels.add(lp);
    }

    public void deleteMatchesPanel(TournamentStage level) {
        Iterator<LevelMatchesPanel> it = levelMatchesPanels.iterator();
        while (it.hasNext()){
            LevelMatchesPanel panel = it.next();
            if(panel.getLevel().equals(level)){
                panel.removeFromParent();
                it.remove();
                return;
            }
        }
    }



}
