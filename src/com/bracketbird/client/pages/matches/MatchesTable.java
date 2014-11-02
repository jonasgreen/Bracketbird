package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class MatchesTable extends FlowPanel {

    private TournamentLevel level;

    public MatchesTable(TournamentLevel tl) {
        super();
        this.level = tl;
        boolean firstRound = true;
        for (Round round : level.getRounds()) {
            if(!firstRound){
                addEmptyLine();
            }
            for (Match match : round.getMatches()) {
                add(new MatchRow(match));
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




}
