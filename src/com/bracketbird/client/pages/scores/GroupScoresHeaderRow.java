package com.bracketbird.client.pages.scores;

import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.ranking.TeamStatistics;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class GroupScoresHeaderRow extends FlowPanel{

    private TeamStatistics teamStatistics;

    private Label groupName;
    private Label playedMatchesHeader;
    private Label scoreHeader;
    private Label pointsHeader;

    private Group group;

    public GroupScoresHeaderRow(Group group) {
        this.group = group;
        setStyleName("groupScoresHeaderRow");
        addStyleName("flex_alignItems_baseline");

        add(getGroupName());
        add(getPlayedMatchesHeader());
        add(getScoreHeader());
        add(getPointsHeader());
    }

    public Label getGroupName() {
        if (groupName == null) {
            groupName = new Label("Group "+group.getName());
            groupName.setStyleName("groupScoreHeaderRow_groupName");
        }
        return groupName;
    }

    public Label getPlayedMatchesHeader() {
        if (playedMatchesHeader == null) {
            playedMatchesHeader = new Label("Pld");
            playedMatchesHeader.addStyleName("groupScoreHeaderRow_playedMatches");
        }
        return playedMatchesHeader;
    }

    public Label getPointsHeader() {
        if (pointsHeader == null) {
            pointsHeader = new Label("Pts");
            pointsHeader.addStyleName("groupScoreHeaderRow_points");
        }
        return pointsHeader;
    }

    public Label getScoreHeader() {
        if (scoreHeader == null) {
            scoreHeader = new Label("Scr");
            scoreHeader.addStyleName("groupScoreHeaderRow_score");
        }
        return scoreHeader;
    }






}
