package com.bracketbird.client.gui.main.personal.personal;

import com.bracketbird.client.*;
import com.bracketbird.client.event.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.client.url.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class ActiveTournaments extends VerticalComponent {

    private SimplePanelComponent tournamentsHolder;
    private LabelComponent createTournament;

    public ActiveTournaments() {
        super();
        init();
    }


    public void init() {
        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getTournamentsHolder(), new TextLayout(Horizontal.LEFT));
        hc.add(getCreateTournament(), new TextLayout(0,0,10,60,Horizontal.RIGHT, Vertical.BOTTOM).colorBaseDark());
        add(hc);
    }


    public SimplePanelComponent getTournamentsHolder() {
        if (tournamentsHolder == null) {
            tournamentsHolder = new SimplePanelComponent();
        }
        return tournamentsHolder;
    }

    public void updateTournaments(Collection<Tournament> tournaments) {
        if (tournaments.isEmpty()) {
            return;
        }
        TextLayout tl = new TextLayout(0, 0, 10, 0);
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent("Active tournaments:"), new TextLayout(0, 0, 15, 0).sizeH3().colorBaseDark());
        for (Tournament t : tournaments) {
            vc.add(createTournamentLabel(t), tl);
        }
        getTournamentsHolder().add(vc);
    }

    public LabelComponent getCreateTournament() {
        if (createTournament == null) {
            createTournament = new LabelComponent("Create new tournament");
            createTournament.setStyleName("colorbutton");
            createTournament.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                //    UserManager.validateIsMember();
/*
                    CreateTournament cr = new CreateTournament();
                    cr.setClubId(UserManager.getInstance().getUser().getPersonalClubId());
                    cr.setNameOfTournament("New_Tournament_"+ DateFormats.dateFormat_tournament.format(new Date()));
                    cr.setTemplate(false);
                    BBService.createTournament(cr, new CallBack<CreateTournamentResult>() {
                        @Override
                        public void success(CreateTournamentResult result) {
                            UrlCommand urlC = UrlCommand.createEditTournament(UrlUtil.getBaseUrl(), new EditTournament(), result.getTournament().getClubId(), result.getTournament().getId());
                            Window.open(urlC.getUrl(), "_blank", null);
                            EventManager.tournamentIsCreated();
                        }

                        @Override
                        public void fail(Throwable t) {

                        }
                    });
                    */
                }
            });
        }
        return createTournament;
    }

    public LabelComponent createTournamentLabel(final Tournament tournament) {
        LabelComponent lc = new LabelComponent(tournament.getName());
        lc.setStyleName("colorbutton2");
        lc.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            }
        });

        return lc;
    }

}