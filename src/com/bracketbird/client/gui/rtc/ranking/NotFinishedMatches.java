package com.bracketbird.client.gui.rtc.ranking;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 *
 */
public class NotFinishedMatches extends MatchesViewGrouping {

    List<MatchView> notFinished;
    private int top;

    public NotFinishedMatches(MatchesViewPanel parent, int top, List<MatchView> matches) {
        super(parent);
        this.notFinished = matches;
        for (MatchView match : matches) {
            match.setParent(this);
            match.getElement().getStyle().setZIndex(MatchesViewGrouping.nextZIndex());
        }
        this.top = top;
        repaint();
    }

    public void setTop(int top) {
        this.top = top;
        repaint();
    }

    @Override
    public void matchChanged(MatchView matchView) {
        if (matchView.getMatch().isFinish()) {
            notFinished.remove(matchView);
            parent.moveToFinished(matchView);
            repaint();
        }
        else {
            notFinished.remove(matchView);
            add(matchView);
        }
    }

    @Override
    public void add(MatchView matchView) {
        matchView.setParent(this);
        matchView.getElement().getStyle().setZIndex(MatchesViewGrouping.nextZIndex());
        int countInProgress = countInProgress();
        if (countInProgress >= notFinished.size()) {
            notFinished.add(matchView);
        }
        else {
            notFinished.add(countInProgress, matchView);
        }
        repaint();
    }

    private int countInProgress() {
        int inProgress = 0;
        for (MatchView m : notFinished) {
            if (m.getMatch().isInProgress()) {
                inProgress++;
            }
            else {
                return inProgress;
            }
        }
        return inProgress;
    }

    private void repaint() {
        System.out.println("REPAINT");
        int index = 0;

        int windowHeight = Window.getClientHeight();
        for (MatchView matchView : notFinished) {
            int topC = calculateTop(index++);
            System.out.println("REPAINT TOPC:" + topC);

            if (topC > windowHeight) {
                matchView.setVisible(false);
            }
            else {
                matchView.setVisible(true);
                matchView.setTop(topC);
            }
        }
    }

    private int calculateTop(int index) {
        return top + (index * MATCH_VIEW_HEIGHT);
    }
}
