package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.client.pages.MenuPanel;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 *
 */
public class TournamentContext extends ApplicationContext<ScrollPanel> {

    private static TournamentContext instance;
    private MenuPanel menuPanel;

    private TournamentContext() {

    }

    public static TournamentContext get() {
        if (instance == null) {
            instance = new TournamentContext();
        }
        return instance;
    }

    public void updateMenuShadow(){
        getMenuPanel().addShadow(getPageContainer().getVerticalScrollPosition() > 0);

    }



    @Override
    protected DockLayoutPanel createContextWidget() {
        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(getMenuPanel(), 62);
        panel.add(getPageContainer());

        getPageContainer().addScrollHandler(new ScrollHandler() {
            @Override
            public void onScroll(ScrollEvent event) {
                updateMenuShadow();
            }
        });
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                updateMenuShadow();
            }
        });

        return panel;
    }

    public MenuPanel getMenuPanel() {
        if (menuPanel == null) {
            menuPanel = new MenuPanel();
        }
        return menuPanel;
    }

    @Override
    protected ScrollPanel createPageContainer() {
        ScrollPanel w = new ScrollPanel();
        w.setStyleName("tournamentContext_scrollPanel");
        return w;
    }


}
