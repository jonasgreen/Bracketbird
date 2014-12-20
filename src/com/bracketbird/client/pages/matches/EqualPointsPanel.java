package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.Team;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.clientcore.util.StringUtil;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.*;

/**
 *
 */
public class EqualPointsPanel extends FlowPanel implements FinalRankingTeamHolder {

    private List<TeamStatistics> results;
    private int start;
    private int end;
    private List<FinalRankRow> rows = new ArrayList<FinalRankRow>();
    private String groupName;

    public EqualPointsPanel(String groupName, List<TeamStatistics> results, int start) {
        super();
        setStyleName("equalPointsPanel");
        this.groupName = groupName;
        this.results = results;
        this.start = start;
        this.end = start + results.size() - 1;
        init();

    }

    private void init() {
        Label w = new Label("Because of equal ranking, set ranking " + start + " to " + end);
        w.setStyleName("equalPointsPanel_title");
        add(w);
        FlowPanel vc = new FlowPanel();
        vc.setStyleName("equalPointsPanel_RowsPanel");
        for (TeamStatistics r : results) {
            FinalRankRow row = new FinalRankRow(r.getTeam(), (String)null);
            rows.add(row);
            vc.add(row);
        }
        add(vc);
    }

    public List<Team> getTeams() {

        Map<Integer, Team> sortedTeams = new TreeMap<Integer, Team>();
        for (FinalRankRow row : rows) {
            sortedTeams.put(Integer.valueOf(row.getTextBox().getValue()), row.getTeam());
        }
        List<Team> teams = new ArrayList<Team>();
        for (Team pt : sortedTeams.values()) {
            teams.add(pt);
        }
        return teams;
    }

    public String validate() {
        Map<Integer, Integer> values = new HashMap<Integer, Integer>();
        String value;
        int intValue;
        //all filled out
        for (FinalRankRow row : rows) {
            try {
                value = row.getTextBox().getValue();
                if (StringUtil.isEmpty(value)) {
                    return getErrorMsgStart() + "Rank value is missing.";
                }
                intValue = Integer.valueOf(value);
                values.put(intValue, intValue);
            }
            catch (Exception e) {
                return getErrorMsgStart() + "Illegal ranking for team " + row.getTeamName().getText() + ". Rank has to be a number.";

            }
        }

        //all numbers used
        int index = start;
        while (index <= end) {
            values.remove(index++);
        }
        if (!values.isEmpty()) {
            return getErrorMsgStart()+ "Illegal ranking sequence. You have to set ranking from " + start + " to " + end + ".";
        }
        return null;
    }

    private String getErrorMsgStart() {
        return groupName != null ? "Rank problem in group " + groupName + ": " : "Rank problem: ";
    }

}
