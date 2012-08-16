package com.bracketbird.client.gui.main.club.tournament;


import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class TournamentsPage extends SubPageHolder<TournamentsPageController> {

    private VerticalSubMenuPanel tournamentsMenuPanel;
    private VerticalSubMenuPanel templatesMenuPanel;
    private VerticalSubMenuPanel challengersMenuPanel;

    private MenuController mc = new MenuController();

    public TournamentsPage() {
        super();

        VerticalMenuPanel is = new VerticalMenuPanel(mc);
        is.addMenuItem(TournamentInfoPageController.getInstance());
        addSection(null, is.getPanel());

        addSection("Challengers", getChallengersMenuPanel().getPanel());

        addSection("Tournaments", getTournamentsMenuPanel().getPanel());

        addSection("Templates", getTemplatesMenuPanel().getPanel());

        loadTemplates();
        loadChallengers();

        //addSection("Templates", null);


    }


    public void setTournamentSubPage(TournamentOverviewPanel top) {
       // setSubPageHolder(top);
    }

    public VerticalSubMenuPanel getTournamentsMenuPanel() {
        if (tournamentsMenuPanel == null) {
            tournamentsMenuPanel = new VerticalSubMenuPanel(mc);
        }
        return tournamentsMenuPanel;
    }

    public VerticalSubMenuPanel getChallengersMenuPanel() {
        if (challengersMenuPanel == null) {
            challengersMenuPanel = new VerticalSubMenuPanel(mc);
        }
        return challengersMenuPanel;
    }

    public VerticalSubMenuPanel getTemplatesMenuPanel() {
        if (templatesMenuPanel == null) {
            templatesMenuPanel = new VerticalSubMenuPanel(mc);
        }
        return templatesMenuPanel;
    }


    public void loadTemplates() {
        final MenuComponent mc1 = new MenuLinkComponent("Tirsdagsturnering");
        mc1.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                mc1.setSelected(true);
                //setSubPageHolder(new TournamentOverviewPage(null));
            }
        });
        getTemplatesMenuPanel().addAnacisticMenuItem(mc1);

        final MenuComponent mc = new MenuLinkComponent("J's liga");
        mc.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                mc.setSelected(true);
                //setSubPageHolder(new TournamentOverviewPage(null));
            }
        });
        getTemplatesMenuPanel().addAnacisticMenuItem(mc);
    }


    public void loadChallengers() {
        final MenuComponent mc1 = new MenuLinkComponent("clublisten");
        mc1.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                mc1.setSelected(true);
                //setSubPageHolder(new TournamentOverviewPage(null));
            }
        });
        getChallengersMenuPanel().addAnacisticMenuItem(mc1);

    }


    public GuiComponent getHeader() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}