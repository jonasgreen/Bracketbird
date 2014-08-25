package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.client.pages.MenuPanel;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

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



    protected void addToPageContainer(PageController pc) {
        ScrollPanel s = new ScrollPanel();
        FlowPanel panel = new FlowPanel();

        int i = 0;
        while(i++ < 1000){
            Label l = new Label("alksjdfælakjsd fælkajsfælakjf");
            panel.add(l);
        }
        s.add(panel);

        getPageContainer().add(s);
    }


    @Override
    protected DockLayoutPanel createPageContainer() {
        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(new MenuPanel(), 62);
        return panel;
    }
}
