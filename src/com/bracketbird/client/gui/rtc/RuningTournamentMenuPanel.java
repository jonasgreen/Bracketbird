package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.pages.teamspage.TeamsPageController;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;


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
            setup(content, EnterResultsPageController.getInstance());
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