package com.bracketbird.client.pages.scores;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.ranking.RankingStep;
import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.HashMap;
import java.util.Map;

public class GroupScoresTable extends FlowPanel {

    private Map<Team, GroupScoresRow> rowMap = new HashMap<>();

    public GroupScoresTable(Group group){
        group.getStatistics().addOnChangeHandler(new UpdateHandler<Match>() {
            @Override
            public void onUpdate(UpdateEvent<Match> event) {
                Team teamHome = event.getNewValue().getTeamHome();
                Team teamOut = event.getNewValue().getTeamOut();

                updateRow(teamHome);
                updateRow(teamOut);

                updateTable(teamHome, teamOut);
            }
        });


        buildScoresTable(group);
    }

    private void buildScoresTable(Group group) {
        for (RankingStep step : group.getStatistics().getRanking()) {
            for (TeamStatistics stat : step.getTeamStatistics()) {
                GroupScoresRow row = new GroupScoresRow(stat);
                rowMap.put(stat.getTeam(), row);
                add(row);
            }
        }
    }

    private void updateTable(Team ... involvedTeams) {

    }

    private void updateRow(Team team) {

    }
}
