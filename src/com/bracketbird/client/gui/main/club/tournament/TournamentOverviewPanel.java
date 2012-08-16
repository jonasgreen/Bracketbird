package com.bracketbird.client.gui.main.club.tournament;

import com.bracketbird.client.event.*;
import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.bracketbird.client.url.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class TournamentOverviewPanel extends VerticalComponent {

    private Tournament tournament;

    private ButtonPanel buttons;

    private ButtonComponent openButton;
    private ButtonComponent deleteButton;

    public TournamentOverviewPanel(Tournament tournament) {
        super();
        this.tournament = tournament;
        init();
    }


    //has no controller - so this method is called internt.

    public void init() {
        add(new LabelComponent(tournament.getName()), RTCLayoutFac2.h3().margin(0,0,20,0));
        add(new HtmlComponent("Her skal være en beskrivelse af turneringen. Fx dens tilstand. Er den gået i gang? osv."));
        add(new SimplePanelComponent(), new TextLayout("100%", null));
        add(getButtons(), new TextLayout(null, "100%").paddingTop(20));
    }

    public ButtonPanel getButtons() {
        if (buttons == null) {
            buttons = new ButtonPanel();
            buttons = new ButtonPanel(Horizontal.LEFT, getOpenButton(), getDeleteButton());
        }
        return buttons;
    }

    public ButtonComponent getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new ButtonComponent("Delete t");
            deleteButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {

                    BBService.deleteTournament(tournament.getId(), new CallBack<VoidResult>() {
                        @Override
                        public void success(VoidResult result) {
                            EventManager.tournamentIsDeleted();
                            InfoManager.showSucces("Tournament deleted with succes");
                        }

                        @Override
                        public void fail(Throwable t) {
                        }
                    });
                }
            });
        }
        return deleteButton;
    }

    public ButtonComponent getOpenButton() {
        if (openButton == null) {
            openButton = new ButtonComponent();
            openButton = new ButtonComponent("Open");
            openButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                   // UrlCommand urlC = UrlCommand.createEditTournament(UrlUtil.getBaseUrl(), new EditTournament(), tournament.getUserId(), tournament.getId());
                   // urlC.open(tournament.getName());
                }
            });
        }
        return openButton;
    }


    public String getHeader() {
        return "Tournament: " + tournament.getName();
    }


}
