package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class EqualPointsPanel extends VerticalComponent implements FinalRankingTeamHolder {


    private List<TeamResultSum> results;
    private int start;
    private int end;
    private List<FinalRankRow> rows = new ArrayList<FinalRankRow>();
    private String groupName;

    public EqualPointsPanel(String groupName, List<TeamResultSum> results, int start) {
        super();
        this.groupName = groupName;
        this.results = results;
        this.start = start;
        this.end = start + results.size() - 1;
        init();
        StyleIt.add(this, new TextLayout());

    }

    private void init() {

        add(new LabelComponent("Because of equal ranking, set ranking " + start + " to " + end), new TextLayout().sizeSmall().colorComplDark().italic().paddingLeft(10));
        VerticalComponent vc = new VerticalComponent();
        TextLayout rLayout = new TextLayout(null, "100%").padding(2, 5, 2, 5);
        for (TeamResultSum r : results) {
            FinalRankRow row = new FinalRankRow(r.getTeam(), (String)null);
            rows.add(row);
            vc.add(row, rLayout);
        }
        add(vc, new TextLayout(1, 5, 1, 5, null, "100%").border(1).borderColor(Color.textComplDark()));
    }

    public List<Team> getTeams() {

        Map<Integer, Team> sortedTeams = new TreeMap<Integer, Team>();
        for (FinalRankRow row : rows) {
            sortedTeams.put(Integer.valueOf(row.getTextBoxComponent().getValue()), row.getTeam());
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
                value = row.getTextBoxComponent().getValue();
                if (StringUtil.isEmpty(value)) {
                    return getErrorMsgStart() + "Rank value is missing.";
                }
                intValue = Integer.valueOf(value);
                values.put(intValue, intValue);
            }
            catch (Exception e) {
                return getErrorMsgStart() + "Illegal ranking for team " + row.getTeamName().getLabel().getText() + ". Rank has to be a number.";

            }
        }

        //all numbers used
        int index = start;
        while (index <= end) {
            values.remove(index++);
        }
        if (!values.isEmpty()) {
            return getErrorMsgStart()+ new StringBuffer("Illegal ranking sequence. You have to set ranking from ").append(start).append(" to ").append(end).append(".").toString();
        }
        return null;
    }

    private String getErrorMsgStart() {
        return groupName != null ? "Rank problem in group " + groupName + ": " : "Rank problem: ";
    }

}
