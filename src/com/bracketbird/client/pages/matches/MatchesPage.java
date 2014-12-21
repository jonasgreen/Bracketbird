package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.appcontrol.Page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class MatchesPage extends Page<MatchesPageController> {

    private List<StagePanel> levelMatchesPanels = new ArrayList<StagePanel>();

    @Override
    protected void init() {
        setStyleName("matchesPage");
    }

    public void addStagePanel(Stage l) {
        StagePanel panel = new StagePanel(l);
        add(panel);
        levelMatchesPanels.add(panel);
    }

    public void deleteStagePanel(Stage stage) {
        Iterator<StagePanel> it = levelMatchesPanels.iterator();
        while (it.hasNext()){
            StagePanel panel = it.next();
            if(panel.getStage().equals(stage)){
                panel.removeFromParent();
                it.remove();
                return;
            }
        }
    }



}
