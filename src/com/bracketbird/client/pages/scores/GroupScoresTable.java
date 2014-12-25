package com.bracketbird.client.pages.scores;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.bracketbird.client.model.ranking.RankingStep;
import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.client.util.ScheduleUtil;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.HashMap;
import java.util.Map;

public class GroupScoresTable extends FlowPanel {

    private static int rowHeight = 30;

    private Map<Team, GroupScoresRow> rowMap = new HashMap<>();
    private Group group;
    private GroupScoresHeaderRow headerRow;



    public GroupScoresTable(Group group) {
        setStyleName("groupScoresTable");
        this.group = group;
        group.getRanking().addOnChangeHandler(new UpdateHandler<Match>() {
            @Override
            public void onUpdate(UpdateEvent<Match> event) {
                Team teamHome = event.getNewValue().getTeamHome();
                Team teamOut = event.getNewValue().getTeamOut();


                final GroupScoresRow row1 = rowMap.get(teamHome);
                final GroupScoresRow row2 = rowMap.get(teamOut);

                addMoveEffects(row1, row2);
                updateTable();

                ScheduleUtil.get().executeLater(1000, new Command() {
                    @Override
                    public void execute() {
                        removeMoveEffects(row1, row2);
                    }
                });
            }
        });


        buildScoresTable(group);
        updateTable();

        this.getElement().getStyle().setHeight(rowHeight + (rowHeight * rowMap.size()), Style.Unit.PX);
    }

    private void addMoveEffects(GroupScoresRow... rows) {
        for (GroupScoresRow r : rows) {
            r.addStyleName("groupScoreRow_move");
        }
    }

    private void removeMoveEffects(GroupScoresRow... rows) {
        for (GroupScoresRow r : rows) {
            r.removeStyleName("groupScoreRow_move");
        }
    }

    private void buildScoresTable(Group group) {
        add(getHeaderRow());
        for (RankingStep step : group.getRanking().getRanking()) {
            for (TeamStatistics stat : step.getTeamStatistics()) {
                GroupScoresRow row = new GroupScoresRow(stat);
                rowMap.put(stat.getTeam(), row);
                add(row);
            }
        }
    }

    private void updateTable() {
        int top = rowHeight; //size of header
        int i = 1;
        for (Team team : group.getRanking().getRankingTeams()) {
            GroupScoresRow row = rowMap.get(team);
            row.getPositionLabel().setValue(i++);
            row.getElement().getStyle().setTop(top, Style.Unit.PX);
            top += rowHeight;
        }
    }


    public GroupScoresHeaderRow getHeaderRow() {
        if (headerRow == null) {
            headerRow = new GroupScoresHeaderRow(group);
        }
        return headerRow;
    }



}
