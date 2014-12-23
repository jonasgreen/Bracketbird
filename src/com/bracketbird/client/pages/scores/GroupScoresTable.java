package com.bracketbird.client.pages.scores;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.bracketbird.client.model.ranking.RankingStep;
import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.ranking.TeamStatistics;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.HashMap;
import java.util.Map;

public class GroupScoresTable extends FlowPanel {

    private Map<Team, GroupScoresRow> rowMap = new HashMap<>();
    private Group group;
    

    public GroupScoresTable(Group group){
        this.group = group;
        group.getRanking().addOnChangeHandler(new UpdateHandler<Match>() {
            @Override
            public void onUpdate(UpdateEvent<Match> event) {
                Team teamHome = event.getNewValue().getTeamHome();
                Team teamOut = event.getNewValue().getTeamOut();

                updateTable(teamHome, teamOut);
            }
        });


        buildScoresTable(group);
        updateTable(null);
    }

    private void buildScoresTable(Group group) {
        for (RankingStep step : group.getRanking().getRanking()) {
            for (TeamStatistics stat : step.getTeamStatistics()) {
                GroupScoresRow row = new GroupScoresRow(stat);
                rowMap.put(stat.getTeam(), row);
                add(row);
            }
        }
    }

    private void updateTable(Team ... involvedTeams) {
        int i = 1;
        for (Team team : group.getRanking().getRankingTeams()) {
            rowMap.get(team).getPositionLabel().setValue(i++);
        }
    }


}
