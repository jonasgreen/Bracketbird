package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.gui.rtc.event.InitEvent;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.gui.rtc.ranking.RankingViewPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.clientcore.appcontrol.PageFlow;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class EventManager {
    private TournamentId tournamentId;

    //Events that has been sent to server with succes.
    private List<REvent> doneEvents = new ArrayList<REvent>();

    private ToServer toServer;
    private FromServer fromFromServer;


    public EventManager(TournamentId tournamentId, FromServer channelFrom) {
        this.tournamentId = tournamentId;
        this.fromFromServer = channelFrom;
        this.toServer = new ToServer(this);
        channelFrom.setReceiver(this);
    }

    public void start(List<REvent> doneEvs, boolean justCreated) {
        executeInitialServerEvents(doneEvs);
        if (RTC.getInstance().getTournament().isViewOnly()) {
            PageFlow.show(RankingViewPageController.getInstance());

        }
        else if(justCreated){
            PageFlow.show(StartPageController.getInstance());
        }
        else{
            PageFlow.show(TeamsPageController.getInstance());
        }
    }

    public void add(REvent event) {
        REvent lastEvent = doneEvents.get(doneEvents.size() - 1);
        event.setStateId(lastEvent.getStateId());
        toServer.add(event);
    }

    public List<REvent> getDoneEvents() {
        return doneEvents;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    private void executeInitialServerEvents(List<REvent> doneEvs) {
        for (REvent event : doneEvs) {
            if (!(event instanceof InitEvent)) {
                RTC.getInstance().executeEvent(event);
            }
            else{
                LogPageController.getInstance().log(event);
            }
            doneEvents.add(event);
        }
    }

    public void pushedFromServer(REvent event) {
        if (event.isChangeState() && (toServer.isBusy() || toServer.isDirtyGui())) {
            doneEvents.add(event);
            toServer.kill();
            RTC.getInstance().reLoadTournament(doneEvents);
        }
        else {
            doneEvents.add(event);
            RTC.getInstance().executeEvent(event);
        }
    }

    public String getClientId() {
        return fromFromServer.getClientId();
    }

    public void eventSendToServer(REvent event) {
        RTC.getInstance().postServerAction(event);
        doneEvents.add(event);
    }

}
