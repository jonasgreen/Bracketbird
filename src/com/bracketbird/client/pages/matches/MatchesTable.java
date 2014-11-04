package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MatchesTable extends FlowPanel {

    private TournamentLevel level;
    private List<MatchRow> rows = new ArrayList<MatchRow>();

    public MatchesTable(TournamentLevel tl) {
        super();
        this.level = tl;
        boolean firstRound = true;
        for (Round round : level.getRounds()) {
            if(!firstRound){
                addEmptyLine();
            }
            for (Match match : round.getMatches()) {
                MatchRow row = new MatchRow(match, this);
                rows.add(row);
                add(row);
            }
            firstRound = false;
        }
    }


    protected void addEmptyLine(){
        FlowPanel fl = new FlowPanel();
        fl.setStyleName("matches_emptyLine");
        fl.add(new Label(""));
        add(fl);
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
}
