package com.bracketbird.client.pages;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.ShareComponent;
import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.clientcore.gui.PopupManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class MenuPanel extends FlowPanel{


    public MenuPanel() {
        setStyleName("menuPanel");

        //primary menu items to the left
        add(new MenuItem("Teams", TeamsPageController.getInstance()));
        add(new MenuItem("Tournament settings", SettingsPageController.getInstance()));
        add(new MenuItem("Matches", EnterResultsPageController.getInstance()));
        add(new MenuItem("Ranking", RankingViewPageController.getInstance()));

        //secondary menu items to the right
        add(helpMenuItem());
        add(shareMenuItem());
        addStyleName("shadow");
    }

    private Label helpMenuItem() {
        Label help = createSecondaryMenuItem("Help");
        help.setTitle("");
        help.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //TODO
            }
        });

        return help;
    }


    private Label shareMenuItem(){
        Label share = createSecondaryMenuItem("Share");
        share.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ShareComponent gc = new ShareComponent(RTC.getInstance().getTournament().getViewUrl(), RTC.getInstance().getTournament().getUrl());
                PopupManager.show(gc, null);
                gc.getCloseButton().getButton().setFocus(true);
            }
        });

        return share;
    }



    private Label createSecondaryMenuItem(String name) {
        Label l = new Label(name);
        l.setStyleName("menuItem_secondary");
        return l;
    }


}
