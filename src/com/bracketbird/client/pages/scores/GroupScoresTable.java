package com.bracketbird.client.pages.scores;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.event.*;
import com.bracketbird.client.model.ranking.RankingStep;
import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.model.tournament.StageSettings;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.util.ScheduleUtil;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupScoresTable extends FlowPanel {

    private static int rowHeight = 30;
    private static int width = 278;

    private Map<Team, GroupScoresRow> rowMap = new HashMap<>();
    private Group group;
    private GroupScoresHeaderRow headerRow;

    //used to show which teams exceed to next stage
    private FlowPanel proceedingToNextRoundLine;
    private FlowPanel maybeProceedingToNextRoundLine;


    public GroupScoresTable(Group group) {
        setStyleName("groupScoresTable");
        this.group = group;

        buildScoresTable();

        updateScoresTable();
        updateProceedingToNextRoundLines();

        listenForProceedingToNextRoundChanges();
        listenForRankingChanges();

        this.getElement().getStyle().setHeight(rowHeight + (rowHeight * rowMap.size()), Style.Unit.PX);
    }

    private void updateProceedingToNextRoundLines() {
        getProceedingToNextRoundLine().removeFromParent();
        getMaybeProceedingToNextRoundLine().removeFromParent();

        StageSettings settings = getNextRoundsSettings();
        if (settings != null && settings.getMaxNumberOfTeams() != null) {
            int noOfTeams = group.getTeams().size();

            int noOfTeamsProceeding = settings.getMaxNumberOfTeams() / group.getStage().getGroups().size();
            int height = rowHeight + (noOfTeamsProceeding * rowHeight);
            getProceedingToNextRoundLine().setHeight(height + "px");
            if (noOfTeamsProceeding < noOfTeams) {
                add(getProceedingToNextRoundLine());
            }

            boolean hasMaybeProceeding = (settings.getMaxNumberOfTeams() % group.getStage().getGroups().size()) != 0;

            if (hasMaybeProceeding && (noOfTeamsProceeding + 1 < noOfTeams)) {
                getMaybeProceedingToNextRoundLine().setHeight(rowHeight + "px");
                add(getMaybeProceedingToNextRoundLine());
            }
        }
    }

    private StageSettings getNextRoundsSettings() {
        Stage nextStage = RTC.getInstance().getTournament().getNextStage(group.getStage());
        return nextStage != null ? nextStage.getSettings() : null;
    }


    private void listenForProceedingToNextRoundChanges() {
        for (Stage stage : RTC.getInstance().getTournament().getStages()) {
            stage.updateSettingsDispatcher.addHandler(new UpdateHandler<StageSettings>() {
                @Override
                public void onUpdate(UpdateEvent<StageSettings> event) {
                    updateProceedingToNextRoundLines();
                }
            });
        }

        RTC.getInstance().getTournament().stagesDispatcher.addHandler(new CreateDeleteHandler<Stage>() {
            @Override
            public void onCreate(CreateEvent<Stage> event) {
                updateProceedingToNextRoundLines();
                event.getValue().updateSettingsDispatcher.addHandler(new UpdateHandler<StageSettings>() {
                    @Override
                    public void onUpdate(UpdateEvent<StageSettings> event) {
                        updateProceedingToNextRoundLines();
                    }
                });
            }

            @Override
            public void onDelete(DeleteEvent<Stage> event) {
                updateProceedingToNextRoundLines();
            }
        });
    }

    private void listenForRankingChanges() {
        group.getRanking().addOnChangeHandler(new UpdateHandler<Match>() {
            @Override
            public void onUpdate(UpdateEvent<Match> event) {
                Team teamHome = event.getNewValue().getTeamHome();
                Team teamOut = event.getNewValue().getTeamOut();


                final GroupScoresRow row1 = rowMap.get(teamHome);
                final GroupScoresRow row2 = rowMap.get(teamOut);

                addMoveEffects(row1, row2);
                updateScoresTable();

                ScheduleUtil.get().executeLater(1000, new Command() {
                    @Override
                    public void execute() {
                        removeMoveEffects(row1, row2);
                    }
                });
            }
        });
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

    private void buildScoresTable() {
        add(getHeaderRow());

        for (RankingStep step : group.getRanking().getRanking()) {
            for (TeamStatistics stat : step.getTeamStatistics()) {
                GroupScoresRow row = new GroupScoresRow(stat);
                rowMap.put(stat.getTeam(), row);
                add(row);
            }
        }
    }

    private void updateScoresTable() {
        int top = rowHeight; //size of header
        int i = 1;

        for (RankingStep step : group.getRanking().getRanking()) {
            List<Team> teams = step.getTeams();
            String pos = teams.size() == 1 ? ("" + i) : (i + "-" + (i + teams.size() - 1));
            for (Team team : teams) {
                GroupScoresRow row = rowMap.get(team);
                row.getPositionLabel().setText(pos);
                row.getElement().getStyle().setTop(top, Style.Unit.PX);
                top += rowHeight;
            }
            i += teams.size();
        }

    }


    public GroupScoresHeaderRow getHeaderRow() {
        if (headerRow == null) {
            headerRow = new GroupScoresHeaderRow(group);
        }
        return headerRow;
    }

    public FlowPanel getProceedingToNextRoundLine() {
        if (proceedingToNextRoundLine == null) {
            proceedingToNextRoundLine = new FlowPanel();
            proceedingToNextRoundLine.add(new Label(""));
            proceedingToNextRoundLine.setStyleName("groupScoresExceedingLine");
            proceedingToNextRoundLine.setWidth(width + "px");
        }
        return proceedingToNextRoundLine;
    }

    public FlowPanel getMaybeProceedingToNextRoundLine() {
        if (maybeProceedingToNextRoundLine == null) {
            maybeProceedingToNextRoundLine = new FlowPanel();
            maybeProceedingToNextRoundLine.add(new Label(""));
            maybeProceedingToNextRoundLine.setStyleName("groupScoresMaybeExceedingLine");

            maybeProceedingToNextRoundLine.setWidth(width + "px");

        }
        return maybeProceedingToNextRoundLine;
    }


}
