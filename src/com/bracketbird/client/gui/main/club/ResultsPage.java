package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.gui.main.club.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ResultsPage extends Page<ResultsPageController> {

    private VerticalComponent content;
    private MenuController mc = new MenuController();
    private VerticalSubMenuPanel tournamentsMenuPanel;
    private SimplePanelComponent detailsPanel;


    public ResultsPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getTournamentsMenuPanel().getPanel(), new TextLayout(Horizontal.LEFT));
        hc.add(getDetailsPanel(), new TextLayout().paddingLeft(40));
        content.add(hc, new TextLayout().paddingTop(40));
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

    public SimplePanelComponent getDetailsPanel() {
        if (detailsPanel == null) {
            detailsPanel = new SimplePanelComponent();
        }
        return detailsPanel;
    }

    public VerticalSubMenuPanel getTournamentsMenuPanel() {
        if (tournamentsMenuPanel == null) {
            tournamentsMenuPanel = new VerticalSubMenuPanel(mc);
        }
        return tournamentsMenuPanel;
    }


    public void setDetails(TournamentOverviewPanel tp) {
        getDetailsPanel().add(tp);
    }
}