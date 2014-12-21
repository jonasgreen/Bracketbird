package com.bracketbird.client.pages.scores.ranking;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FinishedMatches extends MatchesViewGrouping {

    List<MatchView> finished = new ArrayList<MatchView>();
    private int bottom;

    public FinishedMatches(MatchesViewPanel parent, int bottom, List<MatchView> matches) {
        super(parent);
        this.finished = matches;
        for (MatchView match : matches) {
            match.setParent(this);
            match.getElement().getStyle().setZIndex(nextZIndex());
        }

        setBottom(bottom);
        repaint();
    }


    public void setBottom(int bottom) {
        this.bottom = bottom - MATCH_VIEW_HEIGHT;
        repaint();
    }

    @Override
    public void matchChanged(MatchView matchView) {
        if (!matchView.getMatch().isFinish()) {
            finished.remove(matchView);
            parent.moveToNotFinished(matchView);
            repaint();
        }
    }

    @Override
    public void add(MatchView matchView) {
        matchView.setParent(this);
        matchView.getElement().getStyle().setZIndex(nextZIndex());
        finished.add(0, matchView);
        repaint();
    }

    private void repaint() {
        int index = 0;
        for (MatchView matchView : finished) {
            int top = calculateTop(index++);
            if((top + (2*MATCH_VIEW_HEIGHT)) < 0){
                matchView.setVisible(false);
            }
            else{
                matchView.setVisible(true);
                matchView.setTop(top);
            }
        }
    }

    private int calculateTop(int index) {
        return bottom - (index * MATCH_VIEW_HEIGHT);
    }
}
