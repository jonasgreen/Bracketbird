package com.bracketbird.client.pages.teamspage;


import com.bracketbird.client.gui.rtc.matches.SeedingPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsTable;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.appcontrol.ScrollPanelPage;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class TeamsPage extends ScrollPanelPage<TeamsPageController> {

    private TeamsTable teamsTable;
    private LabelComponent editSeeding;


    public void init() {
        int i = 0;
        while (i++ < 25) {
            add(new TeamsRow());
        }
    }


    public LabelComponent getEditSeeding() {
        if (editSeeding == null) {
            editSeeding = new LabelComponent("Change seeding");
            editSeeding.setStyleName("colorbutton4");
            editSeeding.getLabel().addMouseOverHandler(MouseOver.POINTER);
            editSeeding.getLabel().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                   Application.popUp(SeedingPageController.getInstance());

                }
            });

        }
        return editSeeding;
    }


    public TeamsTable getTeamsTable() {
        if (teamsTable == null) {
      //      teamsTable = new TeamsTable(tm);
        }
        return teamsTable;
    }



    public void setFocus(boolean focus) {
        getTeamsTable().setFocus(focus);
    }

}