package com.bracketbird.client.service;


import com.bracketbird.client.ServerWrapper;
import com.bracketbird.client.gui.util.UID;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.clientcore.model.Model;
import com.bracketbird.clientcore.model.keys.EntityId;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class BBService {


    private static ServerWrapper server = new ServerWrapper();



    public static void getTournament(String url, boolean progressBar, CallBack<TournamentResult> cb) {
        if (progressBar) {
            cb.startProgressBar("getting tournament on server");
        }
        executeWithRetry(new GetTournamentAction(url), cb);
    }

    public static void createChannelToken(TournamentChannelId tournamentChannelId, CallBack<CreateChannelTokenResult> cb) {
        cb.startProgressBar("Creating token");
        executeWithRetry(new CreateChannelTokenAction(UID.getUID(), tournamentChannelId), cb);
    }

    public static void createTournament(CallBack<TournamentResult> cb) {
        executeWithRetry(new CreateTournamentAction(), cb);
    }

    public static void updateRunningTournament(RTCAction action, CallBack<RTCResult> back) {
        //back.startProgressBar("Synchronizing tournament data...");
        executeWithRetry(action, back);
    }

    public static void getRunningTournamentEvent(TournamentId tournamentId, Long eventId, CallBack<GetREventResult> cb) {
        executeWithRetry(new GetREventAction(tournamentId, eventId), cb);
    }


    public static void log(String msg) {
        executeWithRetry(new LogAction(msg), new SilentCallBack() {
            public void success(Object result) {
                //ignore
            }

            public void fail(Throwable t) {
                //ignore
            }
        });
    }


    public static void executeWithRetry(Action a, CallBack cb) {
        server.execute(a, cb);
    }



}
