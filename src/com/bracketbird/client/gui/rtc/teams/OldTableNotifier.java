package com.bracketbird.client.gui.rtc.teams;

/**
 *
 */
public interface OldTableNotifier {
    public void up(TeamsTableRowOld tr, int column);
    public void down(TeamsTableRowOld tr, int column);
    public void focusLost(TeamsTableRowOld tr);
}
