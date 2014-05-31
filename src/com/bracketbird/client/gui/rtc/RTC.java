package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.Bracketbird;
import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.gui.util.UID;
import com.bracketbird.client.model.keys.MatchId;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.keys.TournamentLevelId;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.PopupManager;
import com.bracketbird.clientcore.gui.TopPanelHolder;
import com.bracketbird.clientcore.util.CU;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;

import java.util.*;

/**
 * All events originated from User interaction goes through this class.
 * <p/>
 * RTC also listens for relevant state changes events from the tournament to notify server about theese.
 */
public class RTC {


    private boolean reloading = false;


    private Map<Class<?>, REventHandler<REvent<?, ?>>> handlers = new HashMap<Class<?>, REventHandler<REvent<?, ?>>>();
    private FromServer from;

    //listeners for state events that should be notified to server.
    private TournamentListener<TournamentStateChangedEvent> tournamentStateListener = new TournamentListener<TournamentStateChangedEvent>() {
        public void onChange(TournamentStateChangedEvent event) {
            if (!event.isClientEvent()) {
                return;
            }
            TournamentState newState = event.getNewState();
            TournamentState oldState = event.getOldState();
            //server should not be notified if stateshift is between ready and notready.
            if ((newState.equals(new NotReady()) || newState.equals(new Ready())) && (oldState.equals(new NotReady()) || oldState.equals(new Ready()))) {
                return;
            }

            //serverSync.stateChanged(event);
        }
    };

    private TournamentListener<LevelStateEvent> levelStateListener = new TournamentListener<LevelStateEvent>() {
        public void onChange(LevelStateEvent event) {
            if (!event.isClientEvent()) {
                return;
            }
            // serverSync.stateChanged(event);
        }
    };


    private EventManager sync;
    private Tournament tournament;
    private static RTC instance;

    private RTC() {
        initHandlers();
    }

    private void initHandlers() {
        //Teams
        add(new CreateTeamEventHandler());
        add(new UpdateTeamNameEventHandler());
        add(new UpdateTeamInfoEventHandler());
        add(new UpdateTeamSeedingEventHandler());
        add(new DeleteTeamEventHandler());
        add(new UpdateSeedingEventHandler());

        //Level settings
        add(new CreateLevelEventHandler());
        add(new UpdateLevelEventHandler());
        add(new DeleteLevelEventHandler());


        //Matches
        add(new LayoutMatchesEventHandler());
        add(new UpdateMatchResultEventHandler());
        add(new LevelFinishedEventHandler());
        add(new UpdateMatchFieldEventHandler());
    }

    private void add(REventHandler handler) {
        handlers.put(handler.getClass(), handler);
    }

    public static RTC getInstance() {
        if (instance == null) {
            instance = new RTC();
        }
        return instance;
    }


    public void loadTournament(Tournament t, List<REvent> events, boolean justCreated) {
        if(t.isViewOnly()){
            Document.get().getBody().getStyle().setBackgroundColor("black");
        }
        this.tournament = t;
        tournament.addStateListener(tournamentStateListener);

        //init gui and set listeners
        initGuiListeners(t);

        String tName = t.getName();
        Window.setTitle(tName);
        RunningTournamentTop.getInstance().getTournamentName().setText(tName);

        RTC.getInstance().loadServerSync(events, justCreated);

        PopupManager.hide();

        String url = t.isViewOnly() ? t.getViewUrl() : t.getUrl();
        Bracketbird.tournamentUrl =  url;
        com.google.gwt.user.client.History.newItem(url, false);

        if(t.isViewOnly()){
            RunningTournamentTop.getInstance().getTournamentName().getElement().getStyle().setColor("white");
        }

    }

    private void initGuiListeners(Tournament t) {
        if (!t.isViewOnly()) {
            TeamsPageController.getInstance().getPage();
            SettingsPageController.getInstance().getPage();
            EnterResultsPageController.getInstance().getPage();
        }
        RankingViewPageController.getInstance().getPage();
    }

    public void loadServerSync(List<REvent> eventLog, boolean justCreated) {
        from = new FromServer();
        sync = new EventManager(getTournament().getId(), from);

        if (eventLog.isEmpty()) {
            REvent initEvent = new InitEvent(1L, tournament.getId());
            initEvent.setStateId(0L);
            eventLog.add(initEvent);
        }
        from.openChannel(sync, eventLog, justCreated);
    }


    public Tournament getTournament() {
        return tournament;
    }

    private void tournamentFinished() {
        //TODO
    }


    public void postServerAction(REvent<?, ?> event) {
        REventHandler<REvent<?, ?>> handler = handlers.get(event.getHandler());
        handler.postServerAction(event);
    }


    public void executeEvent(REvent<?, ?> event) {
        REventHandler<REvent<?, ?>> handler = handlers.get(event.getHandler());
        if (handler == null) {
            Window.alert("No handler registered for event: " + event.getClass().getName());
        }
        LogPageController.getInstance().log(event);
        handler.handleEvent(event);
    }

    //TEAMS


    public void createTeam() {
        executeEvent(new CreateTeamEvent(null, new TeamId(UID.getUID())));
    }

    public void updateTeamName(TeamId id, String name) {
        executeEvent(new UpdateTeamNameEvent(null, id, name));
    }

    public void updateSeeding(List<TeamId> seedings) {
        executeEvent(new UpdateSeedingEvent(null, getTournament().getId(), seedings));
    }


    public void updateTeamInfo(TeamId id, String info) {
        executeEvent(new UpdateTeamInfoEvent(null, id, info));
    }


    public void updateTeamSeeding(TeamId id, Integer seeding) {
        executeEvent(new UpdateTeamSeedingEvent(null, id, seeding));
    }

    public void deleteTeam(TeamId id) {
        executeEvent(new DeleteTeamEvent(null, id));
    }

    //LEVELS
    public void createLevel(Integer levelType) {
        executeEvent(new CreateLevelEvent(null, levelType, new TournamentLevelId(UID.getUID())));
    }

    public void updateLevelSettings(TournamentLevelId levelId, LevelSettings ls) {
        executeEvent(new UpdateLevelEvent(null, levelId, ls));
    }

    public void deleteLevel(TournamentLevelId levelId) {
        executeEvent(new DeleteLevelEvent(null, levelId));
    }

    //MATCHES
    public void layoutMatches(TournamentLevelId levelId) {
        TournamentLevel previousLevel = getTournament().getPreviousLevel(getTournament().getLevel(levelId));
        if (previousLevel != null && !(previousLevel.getState() instanceof LevelStateInFinished)) {
            OkWarning gc = new OkWarning("You cannot layout matches of a level before the previous level is finished");
            gc.getHeader().setText("Please finish previous level first");
            PopupManager.show(gc, null);
            gc.getOkButton().getButton().setFocus(true);
        }
        else {
            executeEvent(new LayoutMatchesEvent(null, levelId));
        }
    }

    public void updateMatchResult(MatchId id, Result result) {
        if (result == null) {
            executeEvent(new UpdateMatchResultEvent(null, id, null, null));
        }
        else {
            executeEvent(new UpdateMatchResultEvent(null, id, CU.toIntArray(result.getScoresHome()), CU.toIntArray(result.getScoresOut())));
        }
    }

    public void updateMatchField(MatchId id, String field) {
        executeEvent(new UpdateMatchFieldEvent(null, id, field));
    }


    public void levelFinished(TournamentLevelId levelId, List<TeamId[]> finalRanking) {
        executeEvent(new LevelFinishedEvent(null, levelId, finalRanking));
    }


    public void nameChanged(String value, boolean fromClient) {
        //tournament.updateName(value, fromClient);
    }

    public void reLoadTournament(List<REvent> allEvents) {
        //RTCEventPrinter.printEvents(allEvents, "reLoadTournament");

        //throw away changes and bring client up to date
        Tournament t = new Tournament();
        t.setId(tournament.getId());
        t.setName(tournament.getName());
        tournament = t;
        tournament.addStateListener(tournamentStateListener);

        MatchFac.reset();
        //clean gui
        clearGui();

        //will automatically reload the given events.
        sync = new EventManager(getTournament().getId(), from);
        sync.start(allEvents, false);

    }

    private void clearGui() {
        TeamsPageController.getInstance().clear();
        SettingsPageController.getInstance().clear();
        EnterResultsPageController.getInstance().clear();
        RankingViewPageController.getInstance().clear();

        initGuiListeners(getTournament());
        PageFlow.show(PageFlow.activeController);

    }

    public EventManager getSync() {
        return sync;
    }


    public void handleFailure(Throwable t) {
        if (t != null) {
            t.printStackTrace();
        }

    }

}
