package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.pages.matches.MatchesPageController;
import com.bracketbird.client.pages.teams.TeamsPageController;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.AbstractMenuPanel;
import com.bracketbird.clientcore.gui.HorizontalComponent;
import com.bracketbird.clientcore.gui.MenuComponent;


/**
 *
 */
public class RuningTournamentMenuPanel extends AbstractMenuPanel {

    private HorizontalComponent content;


    public void addSectionTitle(String title) {
        //ignore
    }

    protected void add(MenuComponent mc) {

    }


    public HorizontalComponent getPanel() {
        if (content == null) {
            content = new HorizontalComponent();
            setup(content, TeamsPageController.getInstance());
            setup(content, SettingsPageController.getInstance());
            setup(content, MatchesPageController.getInstance());
            setup(content, RankingViewPageController.getInstance());
            setup(content, LogPageController.getInstance());


        }
        return content;
    }

    public void setup(HorizontalComponent hc, PageController pc) {
        addMenuItem(pc);
       // hc.add(pc.getMenu(), new TextLayout(Vertical.BOTTOM).paddingRight(10).paddingLeft(10));
        //pc.getMenu().setVisible(false);
    }


}