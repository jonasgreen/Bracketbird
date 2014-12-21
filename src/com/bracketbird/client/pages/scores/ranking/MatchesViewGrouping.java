package com.bracketbird.client.pages.scores.ranking;

/**
 *
 */
public abstract class MatchesViewGrouping {

    protected static int ZINDEX = 100;
    public static int MATCH_VIEW_HEIGHT = 34;

    protected MatchesViewPanel parent;

    protected MatchesViewGrouping(MatchesViewPanel parent) {
        this.parent = parent;
    }

    public abstract void matchChanged(MatchView widgets);

    public abstract void add(MatchView matchView);

    public static int nextZIndex(){
        return ZINDEX++;
    }
}
