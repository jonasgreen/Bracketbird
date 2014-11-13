package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.google.gwt.dom.client.Style;
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
        int i = 1;
        for (final Round round : level.getRounds()) {
            final FlowPanel roundPanel = createRoundPanel(round.getName());
            add(roundPanel);

            for (Match match : round.getMatches()) {
                if(!match.isWalkover()) {
                    match.setName("" + i++);
                    MatchRow row = new MatchRow(match, this);
                    rows.add(row);
                    roundPanel.add(row);
                }
            }

            com.google.gwt.core.client.Scheduler.get().scheduleDeferred(new com.google.gwt.core.client.Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    Label w = new Label(round.getName());
                    w.setStyleName("matches_roundName");
                    double top = ((double)roundPanel.getOffsetHeight()) /2;
                    System.out.println("top: "+top);
                    w.getElement().getStyle().setTop(8, Style.Unit.PX);
                    roundPanel.add(w);
                }
            });
        }
    }


    protected FlowPanel createRoundPanel(String roundName){
        FlowPanel fl = new FlowPanel();
        fl.setStyleName("matchesTable_roundPanel");
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
}
