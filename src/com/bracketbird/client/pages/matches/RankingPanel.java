package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.Position;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RankingPanel extends FlowPanel {

    private List<FinalRankingTeamHolder> rows = new ArrayList<FinalRankingTeamHolder>();
    boolean hasEqualRanking = false;
    private String title;
    private List<Position> positions;
    private String groupName;

    public RankingPanel(String groupName, List<Position> positions) {
        super();
        setStyleName("rankingPanel");
        this.positions = positions;
        this.title = groupName != null ? "Final ranking group "+groupName : "Final ranking";
        this.groupName = groupName;
        init();
    }

    private void init() {

        Label w = new Label(title);
        w.setStyleName("finalRanking_groupName");
        add(w);

        int positionCount = 1;
        for (Position p : positions) {
            if (p.hasMoreTeams()) {
                hasEqualRanking = true;
                EqualPointsPanel ep = new EqualPointsPanel(groupName, p.getPointsCounters(), positionCount);
                positionCount += p.getPointsCounters().size();
                rows.add(ep);
                add(ep);
            }
            else {
                FinalRankRow row = new FinalRankRow(p.getPointsCounters().get(0).getTeam(), positionCount++);
                rows.add(row);
                add(row);
            }
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

    public boolean hasEqualRanking() {
        return hasEqualRanking;
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<String>();
        if (!hasEqualRanking()) {
            return errors;
        }
        for (FinalRankingTeamHolder row : rows) {
            String errorMessage = row.validate();
            if (errorMessage != null) {
                errors.add(errorMessage);
            }
        }
        return errors;

    }

}
