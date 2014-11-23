package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.Bracketbird;
import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.util.UID;
import com.bracketbird.client.model.StageType;
import com.bracketbird.client.model.keys.MatchId;
import com.bracketbird.client.model.keys.StageId;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.pages.livescores.LiveScoresPageController;
import com.bracketbird.client.pages.matches.MatchesPageController;
import com.bracketbird.client.pages.settings.SettingsPageController;
import com.bracketbird.client.pages.teams.TeamsPageController;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.util.CU;
import com.google.gwt.user.client.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All events originated from User interaction goes through this class.
 * <p/>
 * RTC also listens for relevant state changes events from the tournament to notify server about theese.
 */
public class RTC {


    private boolean reloading = false;


    private Map<Class<?>, REventHandler<REvent<?, ?>>> handlers = new HashMap<Class<?>, REventHandler<REvent<?, ?>>>();
    private FromServer from;


    private EventManager sync;
    private Tournament tournament;
    private static RTC instance;

    private RTC() {
        initHandlers();
    }

    private void initHandlers() {
        //tournament
        //createTournament (name)

        //Teams
        add(new CreateTeamEventHandler());
        add(new UpdateTeamNameEventHandler());
        add(new UpdateTeamSeedingEventHandler());
        add(new DeleteTeamEventHandler());

        add(new UpdateSeedingEventHandler());

        //Level settings
        add(new CreateLevelEventHandler());
        add(new UpdateStageEventHandler());
        add(new DeleteLevelEventHandler());
        add(new LevelFinishedEventHandler());
        //set teamranking(ids[])

        //Matches
        add(new LayoutMatchesEventHandler());
        add(new UpdateMatchResultEventHandler());
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


    public void loadTournament(TournamentResult r, List<REvent> events, boolean justCreated) {
        this.tournament = new Tournament();
        tournament.setViewOnly(r.isViewOnly());
        tournament.setTournamentChannelId(r.getChannelId());
        tournament.setUrl(r.getTournamentUrl());
        tournament.setViewUrl(r.getTournamentViewUrl());
        tournament.setId(r.getTournamentId());

        //init gui and set listeners
        initGuiListeners(tournament);

        Window.setTitle("Tournament");
        //RunningTournamentTop.getInstance().getTournamentName().setText(tName);

        RTC.getInstance().loadServerSync(events, justCreated);

        //PopupManager.hide();

        String url = tournament.isViewOnly() ? tournament.getViewUrl() : tournament.getUrl();
        Bracketbird.tournamentUrl =  url;
        com.google.gwt.user.client.History.newItem(url, false);

        if(tournament.isViewOnly()){
            //RunningTournamentTop.getInstance().getTournamentName().getElement().getStyle().setColor("white");
        }

    }

    private void initGuiListeners(Tournament t) {
        if (!t.isViewOnly()) {
            TeamsPageController.getInstance().getPage();
            SettingsPageController.getInstance().getPage();
            MatchesPageController.getInstance().getPage();
            LiveScoresPageController.getInstance().getPage();
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


    public void createTeam(String teamName, int seeding) {
        executeEvent(new CreateTeamEvent(teamName, seeding, new TeamId(UID.getUID())));
    }

    public void updateTeamName(TeamId id, String name) {
        executeEvent(new UpdateTeamNameEvent(null, id, name));
    }

    public void updateSeeding(List<TeamId> seedings) {
        executeEvent(new UpdateSeedingEvent(null, getTournament().getId(), seedings));
    }


    public void updateTeamSeeding(TeamId id, Integer seeding) {
        executeEvent(new UpdateTeamSeedingEvent(null, id, seeding));
    }

    public void deleteTeam(TeamId id) {
        executeEvent(new DeleteTeamEvent(null, id));
    }

    //LEVELS
    public void createLevel(StageType levelType) {
        executeEvent(new CreateLevelEvent(null, levelType, new StageId(UID.getUID())));
    }

    public void updateLevelSettings(StageId levelId, StageSettings ls) {
        executeEvent(new UpdateStageEvent(null, levelId, ls));
    }

    public void deleteLevel(StageId levelId) {
        executeEvent(new DeleteLevelEvent(null, levelId));
    }

    //MATCHES
    public void layoutMatches(StageId levelId) {
        Stage previousLevel = getTournament().getPreviousLevel(getTournament().getLevel(levelId));
        if(RTC.getInstance().getTournament().getTeams().size() < 2){
            OkWarning gc = new OkWarning("Please add some teams", "There has to be at least two teams to createGroupMatch a tournament.");
            gc.center();
        }
        else if (previousLevel != null && !(previousLevel.isFinished())) {
            OkWarning gc = new OkWarning("Please finish previous level first", "You cannot layout matches of a level before the previous level is finished");
            gc.center();
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


    public void levelFinished(StageId levelId, List<TeamId[]> finalRanking) {
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

        MatchFac.reset();
        //clean gui
        clearGui();

        //will automatically reload the given events.
        sync = new EventManager(getTournament().getId(), from);
        sync.start(allEvents, false);

    }

    private void clearGui() {
        TeamsPageController.getInstance().clear();

        initGuiListeners(getTournament());
        Application.show(Application.get().activePageController());

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
