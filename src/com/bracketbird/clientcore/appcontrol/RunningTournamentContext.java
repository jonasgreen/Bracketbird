package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.client.gui.rtc.RunningTournamentTop;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
 *
 */
public class RunningTournamentContext extends ApplicationContext<DockLayoutPanel> {


    private static RunningTournamentContext instance;

    private RunningTournamentContext() {
    }

    public static RunningTournamentContext get() {
        if (instance == null) {
            instance = new RunningTournamentContext();
        }
        return instance;
    }


    @Override
    protected DockLayoutPanel createPageContainer() {
        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(RunningTournamentTop.getInstance(), 75);
        return panel;
    }
}
