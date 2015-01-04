package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.GroupStage;
import com.google.gwt.user.client.ui.Label;

public class GroupStageRankingPanel extends RankingPanel{

    private GroupStage stage;

    public GroupStageRankingPanel(GroupStage stage) {
       this.stage = stage;

        /*
        for (Group group : stage.getGroups()) {
            add(new GroupRankingPanel(group));
        }
        */
        add(new Label("Group stage ranking"));

    }



}
