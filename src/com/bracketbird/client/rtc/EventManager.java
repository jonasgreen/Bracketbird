package com.bracketbird.client.rtc;

import com.bracketbird.client.rtc.event.InitEvent;
import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.pages.teams.TeamsPageController;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.appcontrol.Application;
import com.bracketbird.client.appcontrol.TournamentContext;

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
        Application.get().shiftApplicationContext(TournamentContext.get());
        Application.show(TeamsPageController.getInstance());

        if (RTC.getInstance().getTournament().isViewOnly()) {
            //TODO
        }
        else{
            Application.show(TeamsPageController.getInstance());
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
                //TODO
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
