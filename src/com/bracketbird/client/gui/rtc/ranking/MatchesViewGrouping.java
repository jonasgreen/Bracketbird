package com.bracketbird.client.gui.rtc.ranking;

/**
 *
 */
public abstract class MatchesViewGrouping {

    protected static int ZINDEX = 100;
    protected int MATCH_VIEW_HEIGHT = 44;

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
