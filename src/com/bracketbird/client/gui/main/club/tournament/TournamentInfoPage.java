package com.bracketbird.client.gui.main.club.tournament;


import com.bracketbird.client.gui.main.personal.personal.CreateTournamentPageController;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class TournamentInfoPage extends SubPage<TournamentInfoPageController> {

    
    public TournamentInfoPage() {
        super();
    }


    public void doInit() {


        ButtonComponent controlPanel = new ButtonComponent("Create tournament");
            controlPanel.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    InfoManager.hideInfo();
                     PageFlow.popUp(CreateTournamentPageController.getInstance());

                }
            });

        getContent().add(controlPanel, LayoutFac.button());






        getContent().add(new SimplePanelComponent(), new TextLayout("100%", null));
    }

    public String getHeader() {
        return "Info";
    }



    public void setFocus() {
    }



    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}