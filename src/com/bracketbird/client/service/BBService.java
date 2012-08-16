package com.bracketbird.client.service;


import com.bracketbird.client.*;
import com.bracketbird.client.gui.util.UID;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.clientcore.language.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class BBService {


    private static ServerWrapper server = new ServerWrapper();


    //Single app

    public static void getSingleAppInfoEdit(UserId uId, ClubId clubId, TournamentId tournamentId, CallBack<SingleResult<SingleAppInfo>> cb) {
        cb.startProgressBar("Loading data");
        executeWithRetry(new GetSingleAppInfoAction(uId, tournamentId, clubId), cb);
    }


    //Tournament


    public static void getTournamentChanges(TournamentId tId, Integer stampId, CallBack<ListResult<Model>> cb) {
        cb.startProgressBar("checking tournament on server");
        executeWithRetry(new GetTournamentChangesAction(tId, stampId), cb);
    }


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

    public static void getActiveTournamentIndex(ClubId clubId, CallBack<ListResult<TournamentIndex>> back) {
        executeWithRetry(new TournamentIndexAction(clubId), back);
    }

    public static void updateRunningTournament(RTCAction action, CallBack<RTCResult> back) {
        //back.startProgressBar("Synchronizing tournament data...");
        executeWithRetry(action, back);
    }

    public static void getRunningTournamentEvent(TournamentId tournamentId, Long eventId, CallBack<GetREventResult> cb) {
        executeWithRetry(new GetREventAction(tournamentId, eventId), cb);
    }

    //TEAM
    /*public static void updateTeam(Match team, CallBack<SingleResult<Match>> cb){
        cb.startProgressBar("Updating team");
        executeWithRetry(new CreateTournamentAction(cr), cb);
    }*/


    //CONSTANT


    //CLUB

    public static void findAllClubs(final CallBack<ListResult<Club>> cb) {
        getAll(FindIn.club, "Finding clubs", cb);
    }

    public static void createClub(final CreateClub crd, final CallBack<ClubResult> cb) {
        cb.startProgressBar("Creating the club: " + crd.getName());
        executeWithRetry(new CreateClubAction(crd), cb);
    }


    public static void findClubsByUser(User user, final CallBack<ListClubsResult> cb) {
        cb.startProgressBar("Finding clubs");
        executeWithRetry(new FindClubsByUserAction(user.getId()), cb);
    }


    public static void deleteTournament(TournamentId tournamentId, CallBack<VoidResult> cb) {
        delete(FindIn.tournament, tournamentId, "Deleting tournament", cb);
    }


    //MEMBERS

    public static void createMember(CreateMember value, CallBack<MemberResult> asyncCallback) {
        asyncCallback.startProgressBar("Creating member");
        CreateMemberAction cma = new CreateMemberAction(value);
        executeWithRetry(cma, asyncCallback);
    }

    public static void deleteMember(MemberId mId, CallBack<VoidResult> cb) {
        delete(FindIn.member, mId, "Delete membership", cb);
    }

    public static void findMembersByUser(UserId uid, CallBack<ListResult<Member>> cb) {
        findBy(FindIn.member, FinderFac.findByEntityId(uid), "Finding members", cb);
    }

    public static void findMembersByClub(ClubId id, final CallBack<ListResult<Member>> cb) {
        findBy(FindIn.member, FinderFac.findByEntityId(id), "Fetching memberinfo", cb);

    }

    public static void getMember(ClubId clubId, UserId userId, CallBack<MemberResult> cb) {
        cb.startProgressBar("Getting member");
        GetMemberAction a = new GetMemberAction(clubId, userId);
        executeWithRetry(a, cb);
    }

    //USER

    public static void updateUser(User user, CallBack<VoidResult> cb) {
        cb.startProgressBar("Updating user info");
        executeWithRetry(new UpdateUserAction(user), cb);
    }

    public static void signIn(SignInData value, CallBack<UserResult> asyncCallback) {
        asyncCallback.startProgressBar("Signing in");
        executeWithRetry(new SigninAction(value), asyncCallback);
    }

    public static void getUserByMail(String email, CallBack<ListResult<User>> cb) {
        cb.startProgressBar(null);
        findBy(FindIn.user, new StringSingleFinder("email", SingleFinder.Operator.EQUAL_TO, email), null, cb);
    }


    public static void createUser(CreateUser value, CallBack<UserResult> asyncCallback) {
        asyncCallback.startProgressBar("Creating user account");
        executeWithRetry(new CreateUserAction(value), asyncCallback);

    }

    public static void deleteUser(UserId userId, CallBack<VoidResult> asyncCallback) {
        asyncCallback.startProgressBar("Deleting account");
        executeWithRetry(new DeleteUserAction(userId), asyncCallback);
    }

    public static void getUser(UserId userId, CallBack<SingleResult<User>> cb) {
        get(FindIn.user, userId, "Getting user", cb);
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

    public static void automaticLogin(UserId userId, CallBack<SingleResult<User>> cb) {
        get(FindIn.user, userId, "Logging in", cb);
    }

    public static void emailPasswordToUser(String email, CallBack<VoidResult> cb) {
        cb.startProgressBar("Sender email");
        executeWithRetry(new EmailPasswordToUserAction(email), cb);
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


    public static void getUUID(int numberOfIds, CallBack<UUIDResult> cb) {
        executeWithRetry(new GetUUIDAction(numberOfIds), cb);
    }


    //CONSTANT

    public static void createConstant(CreateConstant cr, CallBack<SingleResult<Constant>> cb) {
        cb.startProgressBar("Creating " + cr.getGroup().getText());
        executeWithRetry(new CreateConstantAction(cr), cb);
    }

    public static void getConstants(GroupConstants gc, CallBack<ListResult<Constant>> cb) {
        IntegerSingleFinder f = new IntegerSingleFinder("Integer", "group", SingleFinder.Operator.EQUAL_TO, gc.getValue());
        f.setOrdering("name asc");
        findBy(FindIn.constant, f, "", cb);
    }


    public static void createWaitingEmail(String email, final CallBack<VoidResult> cb) {
        cb.startProgressBar("sending email address");
        executeWithRetry(new CreateWaitingMail(email), cb);
    }


    //Ping - to avoid servlet to go to sleep while app is open.

    public static void pingServer(CallBack<VoidResult> cb) {
        cb.startProgressBar("Pinger");
        executeWithRetry(new PingAction(), cb);
    }

    public static void printBackEnd(CallBack<VoidResult> cb) {
        //cb.startProgressBar("PrintBackEnd call");
        //executeWithRetry(new PrintBackEndAction(), cb);
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
        a.setLanguage(Language.language.getValue());
        //cb.setRetry(a);
        server.execute(a, cb);
    }

    public static void resend(Action a, CallBack cb) {
        server.execute(a, cb);
    }


}
