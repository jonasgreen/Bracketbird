package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.gui.main.club.*;
import com.bracketbird.client.gui.main.personal.personal.ActiveTournaments;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ClubHomePage extends Page<ClubHomePageController> implements TopPanelHolder {
    private VerticalComponent content;
    private ActiveTournaments activeTournamentsPanel;
    private ClubHomePageTop topPanel = new ClubHomePageTop();

    public ClubHomePage() {
        super();
        content = new VerticalComponent();
        initWidget(content);




    }




    public GuiComponent getHeader() {
        return null;
    }

    public PageController getDefaultPage() {
        return ClubWallPageController.getInstance();
    }


    @Override
    public void init() {
        content.add(getActiveTournamentsPanel(), new TextLayout(null, "100%"));

/*
        VerticalComponent sp = new VerticalComponent();
        HyperlinkLabelComponent hl = new HyperlinkLabelComponent("Exit club");
        hl.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                UserManager.getInstance().loggedOutOfClub();
            }
        });
        sp.add(hl, new TextLayout().colorBase().underline().sizeSmall());


        hl = new HyperlinkLabelComponent("Create new member");
        hl.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PopUpDataPageController.showCreateMember();
            }
        });
        sp.add(hl, new TextLayout().colorBase().underline().sizeSmall());
        addSection(null, sp);



        VerticalMenuPanel is = new VerticalMenuPanel();
        is.addMenuItem(ClubBoardPageController.getInstance());
        is.addMenuItem(HowClubPageController.getInstance());
        is.addMenuItem(MembersPageController.getInstance());
        addSection("About", is.getPanel());

        addSection("Forum", null);
        **/
    }

    @Override
    protected void setSubPageHolder(Page subPage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public GuiComponent getTopPanel() {
        return topPanel;
    }

    public ActiveTournaments getActiveTournamentsPanel() {
        if (activeTournamentsPanel == null) {
            activeTournamentsPanel = new ActiveTournaments();
        }
        return activeTournamentsPanel;
    }
}