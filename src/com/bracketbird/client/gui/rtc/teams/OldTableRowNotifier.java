package com.bracketbird.client.gui.rtc.teams;

/**
 *
 */
public interface OldTableRowNotifier {
    public void commit(String value);
    public void up();
    public void down();
    public void right();
    public void left();
    public void deleteRow();
    public void focusLost();
    public void focusGained();
}
