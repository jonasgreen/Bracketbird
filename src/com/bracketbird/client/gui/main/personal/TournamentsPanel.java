package com.bracketbird.client.gui.main.personal;

import com.bracketbird.client.*;
import com.bracketbird.client.event.*;
import com.bracketbird.client.gui.main.club.tournament.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class TournamentsPanel extends VerticalComponent {

    private Map<String, TournamentId> tournamentIds = new HashMap<String, TournamentId>();

    private VerticalSubMenuPanel linkPanel;
    private SimplePanelComponent detailHolder = new SimplePanelComponent();

    public TournamentsPanel() {
        super();
        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getLinkPanel().getPanel(), new TextLayout(0,50,0,0));
        hc.add(detailHolder, new TextLayout(null, "300px"));
        add(hc);
          EventManager.onTournamentCreation(new TournamentCreatedHandler() {
              public void onChange(TournamentCreatedEvent event) {
                  loadTournaments();
              }
          });

        EventManager.onTournamentDeletion(new TournamentDeletedHandler() {
            public void onChange(TournamentDeletedEvent event) {
                loadTournaments();
            }
        });
    }

    public VerticalSubMenuPanel getLinkPanel() {
        if (linkPanel == null) {
            linkPanel = new VerticalSubMenuPanel();
        }
        return linkPanel;
    }



    public void loadTournaments() {
        //clearing
        clearingPanelAndMap();
/*
        BBService.getActiveTournamentIndex(UserManager.getInstance().getUser().getPersonalClubId(), new SilentCallBack<ListResult<TournamentIndex>>() {
            @Override
            public void success(ListResult<TournamentIndex> result) {
                for (TournamentIndex ti : result.getResult()) {
                    final MenuLinkComponent mc1 = new MenuLinkComponent(ti.getName());
                    mc1.getLabel().getElement().getStyle().setPadding(2, Style.Unit.PX);

                    mc1.getLabel().getElement().getStyle().setPaddingRight(10, Style.Unit.PX);
                    mc1.getLabel().getElement().getStyle().setPaddingLeft(10, Style.Unit.PX);

                    mc1.addClickHandler(new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            mc1.setSelected(true);
                            BBService.getTournament(tournamentIds.get(mc1.getLabel().getLabel().getText()), new CallBack<SingleResult<Tournament>>() {
                                @Override
                                public void success(SingleResult<Tournament> result) {
                                    detailHolder.add(new TournamentOverviewPanel(result.getResult()));
                                }

                                @Override
                                public void fail(Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                        }
                    });
                    tournamentIds.put(ti.getName(), ti.getId());
                    getLinkPanel().addAnacisticMenuItem(mc1);

                }
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void clearingPanelAndMap() {
        getLinkPanel().removeAll();
        detailHolder.clear();
        tournamentIds = new HashMap<String, TournamentId>();
    }

    public SimplePanelComponent removeDetailHolder(){
        if(detailHolder.getParent() != null){
            detailHolder.removeFromParent();
        }
        return detailHolder;
    }

}