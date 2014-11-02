package com.bracketbird.client.pages.matches;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.model.tournament.TournamentLevelEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.clientcore.appcontrol.PageController;

/**
 *
 */
public class MatchesPageController extends PageController<MatchesPage> {

    private static MatchesPageController instance;

    private MatchesPageController() {
    }

    public static MatchesPageController getInstance() {
        if (instance == null) {
            instance = new MatchesPageController();
        }
        return instance;
    }

    public void afterLoad() {}

    public void beforeUnload() {}

    @Override
    public void afterFirstLoad() {

        RTC.getInstance().getTournament().addLevelListener(new TournamentListener<TournamentLevelEvent>() {
            public void onChange(TournamentLevelEvent event) {
                if (event.getAction() == TournamentLevelEvent.LevelAction.create) {
                    getPage().createMatchesPanel(event.getLevel());
                }
                else if (event.getAction() == TournamentLevelEvent.LevelAction.delete) {
                    getPage().deleteMatchesPanel(event.getLevel());
                }
            }
        });

        for (TournamentLevel l : RTC.getInstance().getTournament().getLevels()) {
            getPage().createMatchesPanel(l);
        }
    }




    public MatchesPage newInstance() {
        return new MatchesPage();
    }





}
