package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.*;
import com.bracketbird.client.event.*;
import com.bracketbird.client.gui.main.club.tournament.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class ResultsPageController extends PageController<ResultsPage> {

    public static String HISTORY_NAME = "club_Results";
    private static ResultsPageController instance;
    private Map<String, TournamentId> tournamentIds = new HashMap<String, TournamentId>();

    private boolean firstLoad = true;


    private ResultsPageController() {
        super(StatisticsResultTournamentPageController.getInstance(), HISTORY_NAME);
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

    public static ResultsPageController getInstance() {
        if (instance == null) {
            instance = new ResultsPageController();
        }
        return instance;
    }

    public ResultsPage newInstance() {
        return new ResultsPage();
    }

    public void afterLoad(){
        if(firstLoad){
            firstLoad = false;
            loadTournaments();
        }
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuSimpleLinkComponent("Tournaments and matches");

    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }



    public void loadTournaments() {
        //clearing
        clearingPanelAndMap();

        BBService.getActiveTournamentIndex(UserManager.getInstance().getClub().getId(), new SilentCallBack<ListResult<TournamentIndex>>() {
            @Override
            public void success(ListResult<TournamentIndex> result) {
                for (TournamentIndex ti : result.getResult()) {
                    final MenuLinkComponent mc1 = new MenuLinkComponent(ti.getName());
                    mc1.addClickHandler(new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            mc1.setSelected(true);
                            BBService.getTournament(tournamentIds.get(mc1.getLabel().getLabel().getText()), new CallBack<SingleResult<Tournament>>() {
                                @Override
                                public void success(SingleResult<Tournament> result) {
                                    getPage().setDetails(new TournamentOverviewPanel(result.getResult()));
                                }

                                @Override
                                public void fail(Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                        }
                    });
                    tournamentIds.put(ti.getName(), ti.getId());
                    getPage().getTournamentsMenuPanel().addAnacisticMenuItem(mc1);

                }
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void clearingPanelAndMap() {
        getPage().getTournamentsMenuPanel().removeAll();
        getPage().getDetailsPanel().add(new SimplePanelComponent());
        tournamentIds = new HashMap<String, TournamentId>();
    }


    public void clear() {
        super.clear();
        clearingPanelAndMap();
        firstLoad = true;
    }
}