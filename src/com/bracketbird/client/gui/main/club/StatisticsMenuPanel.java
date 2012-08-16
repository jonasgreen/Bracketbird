package com.bracketbird.client.gui.main.club;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class StatisticsMenuPanel extends AbstractMenuPanel {

    private HorizontalComponent content;


    public void addSectionTitle(String title) {
        //ignore
    }

    protected void add(MenuComponent mc) {

    }


    public HorizontalComponent getPanel() {
        if (content == null) {
            content = new HorizontalComponent();
            setup(content, ResultsPageController.getInstance());
            setup(content, RankingPageController.getInstance());
            setup(content, StatisticsPageController.getInstance());

        }
        return content;
    }

    private void setup(HorizontalComponent hc, PageController pc) {
        addMenuItem(pc);
        hc.add(pc.getMenu(), new TextLayout(Vertical.BOTTOM).paddingRight(10).paddingLeft(10));
    }


}