package com.bracketbird.client.service;


import com.bracketbird.client.ServerWrapper;
import com.bracketbird.client.gui.util.UID;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.clientcore.model.Model;
import com.bracketbird.clientcore.model.keys.EntityId;
import com.bracketbird.clientcore.model.keys.UserId;
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

    public static void createTournament(String nameOfTournament, UserId id, CallBack<TournamentResult> cb) {
        cb.startProgressBar("Creating tournament");
        executeWithRetry(new CreateTournamentAction(nameOfTournament, id), cb);
    }


    public static void getTournament(TournamentId id, CallBack<SingleResult<Tournament>> cb) {
        get(FindIn.tournament, id, "Getting tournament", cb);
    }


    public static void updateRunningTournament(RTCAction action, CallBack<RTCResult> back) {
        //back.startProgressBar("Synchronizing tournament data...");
        executeWithRetry(action, back);
    }

    public static void getRunningTournamentEvent(TournamentId tournamentId, Long eventId, CallBack<GetREventResult> cb) {
        executeWithRetry(new GetREventAction(tournamentId, eventId), cb);
    }


    public static void refreshServer(){
        executeWithRetry(new RefreshAction(), new CallBack<VoidResult>() {
            @Override
            public void success(VoidResult result) {
                //ignore
            }

            @Override
            public void fail(Throwable t) {
                //ignore
            }
        });
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





    //GENERICS

    public static <M extends Model> void findBy(FindIn findIn, Finder finder, String text, final CallBack<ListResult<M>> cb) {
        cb.startProgressBar(text);
        executeWithRetry(new FindByAction(findIn, finder), cb);
    }

    public static <M extends Model> void getAll(FindIn findIn, String text, final CallBack<ListResult<M>> cb) {
        cb.startProgressBar(text);
        executeWithRetry(new GetAllAction(findIn), cb);
    }

    public static <M extends Model> void get(FindIn findIn, EntityId indexKey, String text, final CallBack<SingleResult<M>> cb) {
        cb.startProgressBar(text);
        executeWithRetry(new GetAction(findIn, indexKey), cb);
    }

    public static void delete(FindIn findIn, EntityId indexKey, String text, final CallBack<VoidResult> cb) {
        cb.startProgressBar(text);
        executeWithRetry(new DeleteAction(findIn, indexKey), cb);
    }

    public static void update(FindIn findIn, Model m, String text, final CallBack<SingleResult> cb) {
        cb.startProgressBar(text);
        executeWithRetry(new UpdateAction(findIn, m), cb);
    }

    public static void executeWithRetry(Action a, CallBack cb) {
        server.execute(a, cb);
    }

    public static void resend(Action a, CallBack cb) {
        server.execute(a, cb);
    }


}
