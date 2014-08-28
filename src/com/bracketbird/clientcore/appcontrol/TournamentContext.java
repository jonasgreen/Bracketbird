package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.client.pages.MenuPanel;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
 *
 */
public class TournamentContext extends ApplicationContext<DockLayoutPanel> {

    private static TournamentContext instance;

    private TournamentContext() {
    }

    public static TournamentContext get() {
        if (instance == null) {
            instance = new TournamentContext();
        }
        return instance;
    }


    @Override
    protected DockLayoutPanel createPageContainer() {
        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(new MenuPanel(), 62);
        return panel;
    }
}
