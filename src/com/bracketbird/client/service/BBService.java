package com.bracketbird.client.service;


import com.bracketbird.client.ServerWrapper;
import com.bracketbird.client.UID;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.service.rtc.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 */
public class BBService {


    private static ServerWrapper server = new ServerWrapper();


    public static void getTournament(String url, AsyncCallback<TournamentResult> cb) {
        executeWithRetry(new GetTournamentAction(url), cb);
    }

    public static void createChannelToken(TournamentChannelId tournamentChannelId, AsyncCallback<CreateChannelTokenResult> cb) {
        executeWithRetry(new CreateChannelTokenAction(UID.next(), tournamentChannelId), cb);
    }

    public static void createTournament(AsyncCallback<TournamentResult> cb) {
        executeWithRetry(new CreateTournamentAction(), cb);
    }

    public static void updateRunningTournament(RTCAction action, AsyncCallback<RTCResult> back) {
        //back.startProgressBar("Synchronizing tournament data...");
        executeWithRetry(action, back);
    }

    public static void getRunningTournamentEvent(TournamentId tournamentId, Long eventId, AsyncCallback<GetREventResult> cb) {
        executeWithRetry(new GetREventAction(tournamentId, eventId), cb);
    }


    public static void executeWithRetry(Action a, AsyncCallback cb) {
        server.execute(a, cb);
    }


}
