package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.gui.rtc.ViewMatch;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CupRankingPanel extends FlowComponent implements RankingPanel {

    private final Cup level;
    private List<DivColumn> columns = new ArrayList<DivColumn>();

    public CupRankingPanel(TournamentLevel l) {
        this.level = (Cup) l;
        this.getStyleElement().getStyle().setDisplay(Style.Display.INLINE);
        level.addStateListener(new TournamentListener<LevelStateEvent>() {
            public void onChange(LevelStateEvent event) {
                if (event.getNewState() instanceof LevelStateInFinished) {
                //    RankingViewPageController.getInstance().getPage().repaint(event);
                }
                else if (event.getNewState() instanceof LevelStateMatchesLayedOut) {
                 //   RankingViewPageController.getInstance().getPage().repaint(event);
                }
                else {
                 //   repaint();
                }
            }
        });
        layoutPanel();
    }

    private void layoutPanel() {
        List<Round> rounds = level.getRounds();
        if (rounds.isEmpty()) {
            return;
        }
        int i = 0;
        Round round = rounds.get(i++);
        addRound(round, getHeader(level, i, rounds));
        while (i < rounds.size()) {
            addDiv(new DivColumn(getArrows(round.getMatches().size() / 2), " "));
            round = rounds.get(i++);
            addRound(round, getHeader(level, i, rounds));
        }

    }

    private String getHeader(Cup level, int round, List<Round> rounds) {
        if (round == rounds.size()) {
            return "The final";
        }
        else if (round + 1 == rounds.size()) {
            return "Semi finals";
        }
        else {
            return "1/" + (level.binaryCount() / round) + " finals";
        }

    }

    public Cup getLevel() {
        return level;
    }

    public FlowComponent getContent() {
        return this;
    }

    public void relayout() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                int height = 0;
                for (DivColumn column : columns) {
                    column.relayout(height);
                    height = column.getElement().getOffsetHeight();
                }
            }
        });
    }

    private void addRound(Round r, String header) {
        List<? extends Match> matches = r.getMatches();
        List<ViewMatch> divs = new ArrayList<ViewMatch>();
        for (Match m : matches) {
            divs.add(new ViewMatch(m));
        }
        addDiv(new DivColumn(divs, header));
    }

    private void addDiv(final DivColumn dc) {
        add(dc);
        columns.add(dc);
    }


    private List<? extends FlowComponent> getArrows(int numberOfArrows) {
        List<FlowComponent> list = new ArrayList<FlowComponent>();
        int count = 0;
        while (count++ < numberOfArrows) {
            FlowComponent fc = new FlowComponent();
            fc.setStyleName("triangle-right");
            list.add(fc);
        }


        return list;
    }
}
