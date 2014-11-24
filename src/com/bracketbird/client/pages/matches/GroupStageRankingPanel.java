package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.GroupStage;
import com.google.gwt.user.client.ui.FlowPanel;

public class GroupStageRankingPanel extends FlowPanel{

    private GroupStage stage;

    public GroupStageRankingPanel(GroupStage stage) {
       this.stage = stage;

        for (Group group : stage.getGroups()) {
            add(new GroupRankingPanel(group));
        }
    }



}
