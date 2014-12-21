package com.bracketbird.client.pages.scores;

import com.bracketbird.client.Css;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.client.rtc.event.UpdateEvent;
import com.bracketbird.client.rtc.event.UpdateHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class GroupScoresRow extends FlowPanel{

    private TeamStatistics teamStatistics;
    private Label teamLabel;
    private Label scoreLabel;

    public GroupScoresRow(TeamStatistics stat) {
        this.teamStatistics = stat;
        Css.style(this, "groupScoresRow", "flex_alignItems_center");

        add(getTeamLabel());
        add(getScoreLabel());
    }


    public Label getTeamLabel() {
        if (teamLabel == null) {
            teamLabel = new Label(teamStatistics.getTeam().getName());
            Css.style(teamLabel, "teamLabel");
            teamStatistics.getTeam().nameDispatcher.addHandler(new UpdateHandler<String>() {
                @Override
                public void onUpdate(UpdateEvent<String> event) {
                    teamLabel.setText(event.getNewValue());
                }
            });
        }
        return teamLabel;
    }

    public Label getScoreLabel() {
        if (scoreLabel == null) {
            scoreLabel = new Label(teamStatistics.getTotalScoreSheet().getPoints() + "");
            Css.style(teamLabel, "scoreLabel");
        }
        return scoreLabel;
    }

}
