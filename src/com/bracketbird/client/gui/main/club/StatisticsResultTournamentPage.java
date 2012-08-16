package com.bracketbird.client.gui.main.club;


import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class StatisticsResultTournamentPage extends Page<StatisticsResultTournamentPageController> {

    private VerticalComponent content;
    private SimplePanelComponent subPageHolder;
    private Page subPage;

    public StatisticsResultTournamentPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        content.add(new StatisticsMenuPanel().getPanel());
        content.add(getSubPageHolder());
    }

    public SimplePanelComponent getSubPageHolder() {
        if (subPageHolder == null) {
            subPageHolder = new SimplePanelComponent();
        }
        return subPageHolder;
    }


    protected void setSubPageHolder(Page subPage) {
        this.subPage = subPage;
        getSubPageHolder().add(subPage);
    }

    public Page getSubPage() {
        return subPage;
    }
}