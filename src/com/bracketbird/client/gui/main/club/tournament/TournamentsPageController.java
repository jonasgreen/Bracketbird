package com.bracketbird.client.gui.main.club.tournament;


import com.bracketbird.client.*;
import com.bracketbird.client.event.*;
import com.bracketbird.client.gui.main.club.*;
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
public class TournamentsPageController extends PageController<TournamentsPage> {


    public static String HISTORY_NAME = "club_tournaments";
    private static TournamentsPageController instance;


    private Map<String, TournamentId> tournamentIds = new HashMap<String, TournamentId>();
    private boolean firstLoad = true;

    private TournamentsPageController() {
        super(ClubHousePageController.getInstance(), HISTORY_NAME);

        EventManager.onTournamentCreation(new TournamentCreatedHandler() {
            public void onChange(TournamentCreatedEvent event) {
                loadTournaments();
            }
        });

    }

    public static TournamentsPageController getInstance() {
        if (instance == null) {
            instance = new TournamentsPageController();
        }
        return instance;
    }

    public TournamentsPage newInstance() {
        return new TournamentsPage();
    }

    public void afterLoadFromHistory() {
        afterLoad();
    }

    public void showit() {

    }


    public void afterLoad() {
        if (getChild() != null) {
            PageFlow.show(getChild());
        }
        else {
            PageFlow.show(TournamentInfoPageController.getInstance());
        }
        if (firstLoad) {
            loadTournaments();
            firstLoad = false;
        }
    }

    public void loadTournaments() {
        getPage().getTournamentsMenuPanel().removeAll();

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
                                    getPage().setTournamentSubPage(new TournamentOverviewPanel(result.getResult()));
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


    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLabelComponent("Tournaments");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }

    public void clear() {
        super.clear();
        firstLoad = true;
        tournamentIds = new HashMap<String, TournamentId>();
    }


}