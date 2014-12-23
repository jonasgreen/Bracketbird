package com.bracketbird.client.pages.scores;

import com.bracketbird.client.ranking.ScoreSheet;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class GroupScoresRow extends FlowPanel{

    private TeamStatistics teamStatistics;

    private AnimatedNumber positionLabel;
    private Label teamLabel;
    private AnimatedNumber matchesLabel;
    private AnimatedNumber scoreLabel;
    private AnimatedNumber pointsLabel;


    public GroupScoresRow(TeamStatistics stat) {
        this.teamStatistics = stat;
        setStyleName("groupScoresRow");
        addStyleName("flex_alignItems_center");

        add(getPositionLabel());
        add(getTeamLabel());
        add(getMatchesLabel());
        add(getScoreLabel());
        add(getPointsLabel());

        teamStatistics.getTeam().nameDispatcher.addHandler(new UpdateHandler<String>() {
            @Override
            public void onUpdate(UpdateEvent<String> event) {
                getTeamLabel().setText(event.getNewValue());
            }
        });

        stat.scoreSheetDispatcher.addHandler(new UpdateHandler<ScoreSheet>() {
            @Override
            public void onUpdate(UpdateEvent<ScoreSheet> event) {
                updateRow(event);
            }
        });
    }

    private void updateRow(UpdateEvent<ScoreSheet> event) {
        getMatchesLabel().setValue(event.getNewValue().getPlayedMatches());
        getScoreLabel().setValue(event.getNewValue().getScoredGoals());
        getPointsLabel().setValue(event.getNewValue().getPoints());
    }


    public Label getTeamLabel() {
        if (teamLabel == null) {
            teamLabel = new Label(teamStatistics.getTeam().getName());
            teamLabel.setStyleName("groupScoreRow_team");
        }
        return teamLabel;
    }

    public AnimatedNumber getMatchesLabel() {
        if (matchesLabel == null) {
            matchesLabel = new AnimatedNumber(teamStatistics.getTotalScoreSheet().getPlayedMatches());
            matchesLabel.setStyleName("groupScoreRow_matches");
        }
        return matchesLabel;
    }

    public AnimatedNumber getScoreLabel() {
        if (scoreLabel == null) {
            scoreLabel = new AnimatedNumber(teamStatistics.getTotalScoreSheet().getScoredGoals());
            scoreLabel.setStyleName("groupScoreRow_score");
        }
        return scoreLabel;
    }

    public AnimatedNumber getPointsLabel() {
        if (pointsLabel == null) {
            pointsLabel = new AnimatedNumber(teamStatistics.getTotalScoreSheet().getPoints());
            pointsLabel.setStyleName("groupScoreRow_points");
        }
        return pointsLabel;
    }

    public AnimatedNumber getPositionLabel() {
        if (positionLabel == null) {
            positionLabel = new AnimatedNumber(0);
            positionLabel.setStyleName("groupScoreRow_position");
        }
        return positionLabel;
    }

}
