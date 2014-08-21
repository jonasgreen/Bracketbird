package com.bracketbird.client.pages;

import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class MenuPanel extends FlowPanel{


    public MenuPanel() {
        setStyleName("menuPanel");
        add(new MenuItem("Teams", TeamsPageController.getInstance()));
        add(new MenuItem("Tournament settings", SettingsPageController.getInstance()));
        add(new MenuItem("Matches", EnterResultsPageController.getInstance()));
        add(new MenuItem("Ranking", RankingViewPageController.getInstance()));
    }


}
