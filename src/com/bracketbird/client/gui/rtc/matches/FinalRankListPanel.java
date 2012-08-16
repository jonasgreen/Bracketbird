package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class FinalRankListPanel extends VerticalComponent {
    public static TextLayout rLayout = new TextLayout(null, "100%").padding(2, 5, 2, 0);
    public static TextLayout titleLayout = new TextLayout().sizeH2().colorBaseDark().padding(10).paddingBottom(15);

    private List<FinalRankingTeamHolder> rows = new ArrayList<FinalRankingTeamHolder>();
    boolean hasEqualRanking = false;
    private SimplePanelComponent content = new SimplePanelComponent();
    private String title;
    private List<Position> positions;
    private String groupName;

    public FinalRankListPanel(String groupName, List<Position> positions) {
        super();
        this.positions = positions;
        this.title = groupName != null ? "Final ranking group "+groupName : "Final ranking";
        this.groupName = groupName;
        init();

        StyleIt.add(this, new TextLayout());
    }

    private void init() {

        add(new LabelComponent(title), titleLayout);
        add(content, new TextLayout(0,0,0,10));
        VerticalComponent vc = new VerticalComponent();

        int positionCount = 1;
        for (Position p : positions) {
            if (p.hasMoreTeams()) {
                hasEqualRanking = true;
                EqualPointsPanel ep = new EqualPointsPanel(groupName, p.getPointsCounters(), positionCount);
                positionCount += p.getPointsCounters().size();
                rows.add(ep);
                vc.add(ep, new TextLayout(null, "100%"));
            }
            else {
                FinalRankRow row = new FinalRankRow(p.getPointsCounters().get(0).getTeam(), positionCount++);
                rows.add(row);
                vc.add(row, rLayout);
            }
        }
        content.add(vc);
    }

    //returning final ranking
    public List<Team> done() {
        VerticalComponent vc = new VerticalComponent();
        List<Team> teams = new ArrayList<Team>();
        for (FinalRankingTeamHolder row : rows) {
            teams.addAll(row.getTeams());
        }
        int count = 1;
        for (Team team : teams) {
            vc.add(new FinalRankRow(team, count++));
        }
        content.add(vc);
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
