package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.gui.rtc.matches.EnterResultsPage;
import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.bracketbird.clientcore.style.StyleIt;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StartPageComponentManager {

    private List<StartPageComponent> components = new ArrayList<StartPageComponent>();

    private List<com.google.gwt.user.client.Element> topPanelElements = new ArrayList<com.google.gwt.user.client.Element>();

    public StartPageComponentManager() {
        topPanelElements.add(RunningTournamentTop.getInstance().getFeedback().getElement());
        topPanelElements.add(RankingViewPageController.getInstance().getMenu().getElement());
        topPanelElements.add(LogPageController.getInstance().getMenu().getElement());
        topPanelElements.add(TeamsPageController.getInstance().getMenu().getElement());
        topPanelElements.add(SettingsPageController.getInstance().getMenu().getElement());
        topPanelElements.add(EnterResultsPageController.getInstance().getMenu().getElement());


        for (Element elm : topPanelElements) {
            elm.getStyle().setProperty("opacity", "0");

            StyleIt.setTransitionsTime(elm, 1);
        }
    }

    public StartPageComponent add(StartPageComponent c){
        c.setManager(this);
        components.add(c);

        StyleIt.setTransitionsTime(c.getLabel().getElement(), .5d);
        StyleIt.setTransitionsTime(c.getImage().getElement(), 2d);

        return c;
    }


    public void clicked(final StartPageComponent c){
        for (StartPageComponent component : components) {
            component.animateLabel();
        }

        Timer t = new Timer() {
            @Override
            public void run() {
                for (StartPageComponent component : components) {
                    component.animateImage();

                }
            }
        };

        t.schedule(1000);


        Timer t3 = new Timer() {
            @Override
            public void run() {
                for (Element elm : topPanelElements) {
                    elm.getStyle().setProperty("opacity", "1");
                }
            }
        };

        t3.schedule(3300);



        Timer t2 = new Timer() {
            @Override
            public void run() {
                for (StartPageComponent component : components) {
                    component.removeFromParent();
                }
                for (Element elm : topPanelElements) {
                    StyleIt.setTransitionsTime(elm, 0);
                }
                PageFlow.show(c.getPageController());
            }
        };

        t2.schedule(4300);




    }


}
