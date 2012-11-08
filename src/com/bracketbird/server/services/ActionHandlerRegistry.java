package com.bracketbird.server.services;

import com.bracketbird.server.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.server.repository.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class ActionHandlerRegistry {

    //Obs - for performance issues this class could be converted to a relative singleton (relativ to each servlet)
    // because of the synchronization in the bottom at set- and getRequest methods.

    private static ServletContext context;
    private static Map<FindIn, Repository> repos = new HashMap<FindIn, Repository>();
    private static String baseUrl;

    private static boolean isLoaded = false;
    private static Map<Class, ActionHandler> handlers = new HashMap<Class, ActionHandler>();

    public static synchronized void init(ServletContext servletContext) {
        if (isLoaded) {
            return;
        }
        context = servletContext;
        Logger.init(servletContext);

        //loading constants - a bit ugly
        initRepositories();
        initHandlers();
        isLoaded = true;
    }

    private static void initRepositories() {
        repos.put(FindIn.club, new ClubRepository());
        repos.put(FindIn.user, new UserRepository());
        repos.put(FindIn.member, new MemberRepository());
        repos.put(FindIn.tournament, new TournamentRepository());
        repos.put(FindIn.constant, new ConstantRepository());
        repos.put(FindIn.tournamentchannel, new TournamentChannelRepository());
    }


    private static void initHandlers() {
    	
    	
        //SINGLE APP INFO
        add(new GetSingleAppInfoHandler());

        add(new GetUUIDHandler());

        //GENERICS
        add(new GetAllHandler());
        add(new GetHandler());
        add(new FindByHandler());
        add(new DeleteHandler());
        add(new UpdateHandler());

        //USER
        //add(new CreateUserHandler());
        //add(new SigninHandler());
        //add(new DeleteUserHandler());
        //add(new EmailPasswordToUserHandler());
        //add(new UpdateUserHandler());

        //MEMBER
        //add(new CreateMemberHandler());
        //add(new GetMemberHandler());

        //CLUB
        //add(new CreateClubHandler());
        //add(new FindClubsByUserHandler());
        //add(new FindClubsHandler());

        //CONSTANTS
        add(new CreateConstantHandler());

        //TOURNAMENT
        add(new GetTournamentChangesHandler());
        add(new CreateTournamentHandler());
        add(new TournamentIndexHandler());
        add(new GetTournamentHandler());

        //TOURNAMNT CHANNEL
        add(new CreateChannelTokenHandler());

        //RTC (Running tournament control)
        add(new RTCActionHandler());
        add(new GetREventHandler());

        //EMAIL - SUBSCRIPTIONS

        //LOG
        add(new LogHandler());

       //CRON
        add(new RefreshHandler());

        //QUEUE JOB
        add(new WaitingEmailHandler());
        add(new EmailHandler());
    }

    private static void add(ActionHandler handler) {
        handlers.put(handler.getActionType(), handler);
    }


    public static Result executeAction(Action a) throws ApplicationException {
        try {
            ActionHandler actionHandler = getHandler(a);
            if (actionHandler == null) {
                String s = a.getClass().getName() + " has no ActionHandler";
                Logger.log(s);
                throw new SystemException(s);
            }
            actionHandler.setClientLanguage(a.getLanguage());
            return actionHandler.execute(a);
        }
        catch (Throwable t) {
            String s = "Error caught in Servlet: " + t.getClass().getName();
            Logger.log(s, t);
            if (t instanceof SystemException) {
                throw (SystemException) t;
            }
            if (t instanceof ApplicationException) {
                throw (ApplicationException) t;
            }
            throw new SystemException(t.getMessage());
        }
    }

    private static ActionHandler getHandler(Action a) {
        ActionHandler handler = handlers.get(a.getClass());
        if (handler == null) {
            throw new SystemException("ActionHandler not setup for Action: " + a.getClass());
        }
        return handler;
    }

    public static Repository getRepos(FindIn f) {
        Repository r = repos.get(f);
        if (r == null) {
            throw new SystemException("Repository not setup for FindIn:" + f);
        }
        return r;
    }

    public static ServletContext getContext() {
        return context;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setRequestSettings(HttpServletRequest r) {
        if (baseUrl == null) {
            if (r.getServerName().indexOf("http://") != -1) {
                baseUrl = r.getServerName();
            }
            else if (r.getServerName().indexOf("www") != -1) {
                baseUrl = "http://" + r.getServerName();
            }
            else {
                baseUrl = "http://www." + r.getServerName();
            }
        }
    }


}