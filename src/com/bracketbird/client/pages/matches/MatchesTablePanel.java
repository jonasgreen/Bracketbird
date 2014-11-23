package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MatchesTablePanel extends FlowPanel {

    private Stage level;
    private List<MatchRow> rows = new ArrayList<MatchRow>();

    public MatchesTablePanel(Stage tl) {
        super();
        setStyleName("matchesTablePanel");
        this.level = tl;
        int i = 1;
        for (final Round round : level.getRounds()) {
            if(i > 1){
                add(createRoundSeparatorPanel(round.getName()));
            }

            for (Match match : round.getMatches()) {
                if(!match.isWalkover()) {
                    match.setName("" + i++);
                    MatchRow row = new MatchRow(match, this);
                    rows.add(row);
                    add(row);
                }
            }
        }
    }


    protected FlowPanel createRoundSeparatorPanel(String roundName){
        FlowPanel fl = new FlowPanel();
        fl.setStyleName("matchesTable_roundPanel");
        fl.addStyleName("flex_alignItems_center");

        Label w = new Label(roundName);
        w.setStyleName("matches_roundName");
        fl.add(w);

        return fl;
    }


    public void down(MatchRow row) {
        int index = rows.indexOf(row);
        if(index < rows.size()-1){
            rows.get(index+1).getResultTextBox().setFocus(true);
        }
    }

    public void up(MatchRow row) {
        int index = rows.indexOf(row);
        if(index > 0){
            rows.get(index-1).getResultTextBox().setFocus(true);
        }
    }

    public List<MatchRow> getRows() {
        return rows;
    }
}
