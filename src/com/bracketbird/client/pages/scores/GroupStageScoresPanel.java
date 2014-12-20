package com.bracketbird.client.pages.scores;

import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.GroupStage;
import com.google.gwt.user.client.ui.FlowPanel;

public class GroupStageScoresPanel extends FlowPanel{

    public GroupStageScoresPanel(GroupStage stage){
        for (Group group : stage.getGroups()) {
            add(new GroupScoresTable(group));
        }
    }
}
