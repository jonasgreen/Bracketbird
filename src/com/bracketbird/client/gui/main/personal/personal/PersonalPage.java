package com.bracketbird.client.gui.main.personal.personal;

import com.bracketbird.client.*;
import com.bracketbird.client.event.*;
import com.bracketbird.client.gui.main.personal.HomePageTop;
import com.bracketbird.client.gui.main.personal.TournamentsPanel;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.bracketbird.client.url.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class PersonalPage extends Page<PersonalPageController> implements TopPanelHolder{
    private LabelComponent createTournament;

    private VerticalComponent content;

    //private MyClubs myClubsPanel;
    private HomePageTop topPanel = new HomePageTop();
    private TournamentsPanel tournamentsPanel;


    public PersonalPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    @Override
    public void init() {
        HorizontalComponent top = new HorizontalComponent();

        VerticalComponent topLeft = new VerticalComponent();
        topLeft.add(getCreateTournament(), new TextLayout().sizeH3().bold());
        topLeft.add(getTournamentsPanel(), new TextLayout(30, 0,0,0));

        top.add(topLeft, new TextLayout(0,40,0,0));
        top.add(getTournamentsPanel().removeDetailHolder());

        
        content.add(top);



    }

    public TournamentsPanel getTournamentsPanel() {
        if (tournamentsPanel == null) {
            tournamentsPanel = new TournamentsPanel();
        }
        return tournamentsPanel;
    }

    public GuiComponent getTopPanel() {
        return topPanel;
    }



    public LabelComponent getCreateTournament() {
        if (createTournament == null) {
            createTournament = new LabelComponent("Create new tournament");
            createTournament.setStyleName("colorbutton");

            createTournament.addMouseOver(MouseOver.POINTER);
            createTournament.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.popUp(CreateTournamentPageController.getInstance());
                }
            });
        }
        return createTournament;
    }


    @Override
    protected void setSubPageHolder(Page subPage) {
    }
}