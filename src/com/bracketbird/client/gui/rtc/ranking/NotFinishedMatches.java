package com.bracketbird.client.gui.rtc.ranking;

import com.google.gwt.user.client.Window;

import java.util.*;

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
        setTop(top);
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
        notFinished.add(matchView);
        repaint();
    }


    private void repaint() {
        int index = 0;


        List<MatchView> inProgress = new ArrayList<MatchView>();
        Iterator<MatchView> it = notFinished.iterator();
        while (it.hasNext()) {
            MatchView m = it.next();
            if (m.getMatch().isInProgress()) {
                it.remove();
                inProgress.add(m);
            }
        }

        Collections.sort(notFinished, new Comparator<MatchView>() {
            @Override
            public int compare(MatchView matchView, MatchView matchView2) {
                return matchView.getNumber().compareTo(matchView2.getNumber());
            }
        });


        notFinished.addAll(0, inProgress);

        int windowHeight = Window.getClientHeight();
        for (MatchView matchView : notFinished) {
            int topC = calculateTop(index++);

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
