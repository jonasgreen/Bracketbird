package com.bracketbird.client.gui.rtc.teams;

import com.bracketbird.client.gui.rtc.teams.TeamsTableRow;

/**
 *
 */
public interface OldTableNotifier {
    public void up(TeamsTableRow tr, int column);
    public void down(TeamsTableRow tr, int column);
    public void focusLost(TeamsTableRow tr);
}
