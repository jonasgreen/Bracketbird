package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.LevelState;
import com.bracketbird.client.model.tournament.Position;
import com.bracketbird.client.rtc.event.UpdateEvent;
import com.bracketbird.client.rtc.event.UpdateHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupRankingPanel extends FlowPanel {

    private List<EqualPointsPanel> rows = new ArrayList<EqualPointsPanel>();
    private String title;
    private Group group;
    private FlowPanel content = new FlowPanel();

    public GroupRankingPanel(Group group) {
        super();
        this.group = group;
        setStyleName("rankingPanel");
        Label w = new Label(group.getName());
        w.setStyleName("finalRanking_groupName");
        add(w);
        add(content);


        group.addStateHandler(new UpdateHandler<LevelState>() {
            @Override
            public void onUpdate(UpdateEvent<LevelState> event) {
                handleStateChange(event.getNewValue());
            }
        });

        handleStateChange(group.getState());
    }

    private void handleStateChange(LevelState state) {
        if (state.isFinished()) {
            buildFinishedRows(group.getEndingTeams());
        } else if (state.isDonePlaying()) {
            buildDonePlayingRows(group.getGroupPositions());
        } else {
            content.clear();
        }
    }

    private void buildDonePlayingRows(GroupPositions groupPositions) {
        for (Position p : group.getGroupPositions().getPositionOfTeams()) {
            content.add(new Label("" + p.getPointsCounters().size()));
        }
        content.add(new Button("wuhuuu"));

    }

    private void buildFinishedRows(List<Team> endingTeams) {
        for (Team endingTeam : endingTeams) {
            content.add(new Label(endingTeam.getName()));
        }

    }


    //returning final ranking
    public List<Team> done() {
        FlowPanel vc = new FlowPanel();
        List<Team> teams = new ArrayList<Team>();
        for (FinalRankingTeamHolder row : rows) {
            teams.addAll(row.getTeams());
        }
        int count = 1;
        for (Team team : teams) {
            vc.add(new FinalRankRow(team, count++));
        }
        return teams;
    }


    public List<String> validate() {
        List<String> errors = new ArrayList<String>();

        for (FinalRankingTeamHolder row : rows) {
            String errorMessage = row.validate();
            if (errorMessage != null) {
                errors.add(errorMessage);
            }
        }
        return errors;

    }

}
